/**
 * --------------------------------------------------------------------------------------------------------------------
 * HEADER : Copyright (c) finnova AG Bankware
 * PROJECT: ZV
 * PACKAGE: ZVQCASA0
 *
 * $Source$
 * $Id$
 * $Revision$
 *
 * Description:
 * Automatisches Cash-Visum setzen nach Timeout
 * --------------------------------------------------------------------------------------------------------------------
 * History:
 * $Log$
 *
 */


/**

*/
create or replace package ZVQCASA0 as

   --------------------------------------------------------------------
   /**
      P_SrJobNr   => Job-Nummer
      P_WfGfLnr   => Workflow-Laufnummer
      P_WfMailLnr => Mail-Laufnummer
   */
   procedure WfAutoStep(P_SrJobNr     in     SR_JOB_ACT.SR_JOB_NR%type,
                        P_WfGfLnr     in     WF_MAIL.WF_GF_LNR%type,
                        P_WfMailLnr   in     WF_MAIL.WF_MAIL_LNR%type);

   --------------------------------------------------------------------
   /**
      Returns the revision of the package

      return   =>
   */
   function  F_Revision return char;

end ZVQCASA0;
/
show err

/**

*/
create or replace package body ZVQCASA0 as

   --------------------------------------------------------------------
   /**
      Verarbeitung.

      P_AufLnr    =>
      P_VerarbDat =>
   */
   procedure Verarbeitung(P_AufLnr      in     number,
                          P_VerarbDat   in     date);

   --------------------------------------------------------------------
   /**
      Schliessen von allfällig noch offenen package-weiten Cursorn.

      P_SrJobNr   =>
      P_WfGfLnr   =>
      P_WfMailLnr =>
      P_Stat      =>

      return      =>
   */
   function  F_VerarbEnd(P_SrJobNr     in     number,
                         P_WfGfLnr     in     number,
                         P_WfMailLnr   in     varchar2,
                         P_Stat        in     number)
                         return number;

   /** Userbank, get once per session. */
   V_Ub                  ZVWOM_AUF.USERBK_NR%type := F2QAPENV.F_UB;

   /* PL/SQL-Tabellen. */

   /* Program parameters */

   /* Global variables */

   /** */
   V_FePar            varchar2(200);

   /* Error handling */
   E_BuchDatErm       exception;           -- Fehler beim Erm. VerarbDatum (ZVPVAKC -1004)
   E_WfFehler         exception;           -- Fehler in WfVerarb (Folgefehler)

   --------------------------------------------------------------------
   /* Declaration of cursors used beyond the scope of one procedure */

   --------------------------------------------------------------------

   --------------------------------------------------------------------
   /**
      P_SrJobNr   => Job-Nummer
      P_WfGfLnr   => Workflow-Laufnummer
      P_WfMailLnr => Mail-Laufnummer
   */
   procedure WfAutoStep(P_SrJobNr     in     SR_JOB_ACT.SR_JOB_NR%type,
                        P_WfGfLnr     in     WF_MAIL.WF_GF_LNR%type,
                        P_WfMailLnr   in     WF_MAIL.WF_MAIL_LNR%type) is

   L_AufLnr        number;
   L_BuchDatAVTag  date;
   L_EndStat       number;
   L_Stat          number;
   L_VerarbDat     date := null;
   L_VerarbDatZeit date;
   L_WfPrio        number := 1;                                     -- 1= ?? WfStep
   L_WfTyp         varchar2(1) := 'G';                              -- G= kein commit WfStep
   L_Zeit          varchar2(20);

   begin
      SRQJDMN0.SetActive(P_SrJobNr);
      L_VerarbDat := null;
      BUQ09010.GetBuchDat(L_VerarbDat , L_BuchDatAVTag);
      if L_VerarbDat is null then
         raise E_BuchDatErm;                                        -- Fehler beim Erm. VerarbDatum
      end if;
      L_Zeit := to_char(sysdate, 'HH24:Mi');
      L_VerarbDatZeit := to_date(to_char(L_VerarbDat, 'YYYYMMDD') || L_Zeit, 'YYYYMMDDHH24:MI');

      select Auf.AUF_LNR into L_AufLnr
      from   ZVWOM_AUF  Auf
      where  Auf.WF_GF_LNR = P_WfGfLnr;

      Verarbeitung(L_AufLnr, L_VerarbDatZeit);
      --zvqswba0.AddReplaceWfVar(L_WfFeldList, 'KP_BENVIS', '0');
      --zvqswba0.AddReplaceWfVar(L_WfFeldList, 'KP_VISBER', '0');
      SRQWKFL0.StepDone(L_WfTyp, P_WfGfLnr, P_WfMailLnr,
                  'KP_BENVIS=0°1KP_VISBER=0', L_WfPrio, L_Stat);                  -- Step done
      if L_Stat <> ALQCNST0.CRS_OK then
         raise E_WfFehler;
      end if;
      L_EndStat := F_VerarbEnd(P_SrJobNr, P_WfGfLnr, P_WfMailLnr, ALQCNST0.CRS_OK);

   exception
      when E_BuchDatErm then                                        -- Fehler beim Erm. VerarbDatum
         V_FePar := null;
         SRQUTIL0.Mess('ZVQCASA0', 'WfAutoStep', 'ZVPVAKC',  -1004,  V_FePar,
                       'FE', SRQUTIL0.V_LastLogLnr, null, SRQUTIL0.V_LastMsgStr);
         L_EndStat := F_VerarbEnd(P_SrJobNr, P_WfGfLnr, P_WfMailLnr, ALQCNST0.CRS_FAILED);

      when E_WfFehler then                                          -- Fehler in WfVerarb
         L_EndStat := F_VerarbEnd(P_SrJobNr, P_WfGfLnr, P_WfMailLnr, ALQCNST0.CRS_FAILED);

      when others then
         L_EndStat := F_VerarbEnd(P_SrJobNr, P_WfGfLnr, P_WfMailLnr, ALQCNST0.CRS_FAILED);
         ZVQF_LOG.ErrorLog(null);

   end WfAutoStep;
   --------------------------------------------------------------------

   --------------------------------------------------------------------
   /**
      Verarbeitung.

      P_AufLnr    =>
      P_VerarbDat =>
   */
   procedure Verarbeitung(P_AufLnr      in     number,
                          P_VerarbDat   in     date) is

   begin
      update ZVWOM_AUF
      set    MUT_VON = F2QAPENV.F_TrxDate, MUT_BIS = ALQCNST0.CMAX_DAT, KD_LNR_VIS = F2QAPENV.F_SU, STAT_CD = decode(STAT_CD,null,100,STAT_CD), VERS_VOR = mod(VERS_VOR + 1,100000),
             OBJ_STAT_CD = 370                                      -- Verbuchung pendent
      where  AUF_LNR     = P_AufLnr;

      update ZV_AUF_T
      set    MUT_VON = F2QAPENV.F_TrxDate, MUT_BIS = ALQCNST0.CMAX_DAT, KD_LNR_VIS = F2QAPENV.F_SU, STAT_CD = decode(STAT_CD,null,100,STAT_CD), VERS_VOR = mod(VERS_VOR + 1,100000),
             ZLG_ANZ_CASH_P = 0
      where  AUF_LNR        = P_AufLnr
      and    USERBK_NR      = V_Ub
      and    VERS            = ALQCNST0.CVERS_HIGH_VALUE;

      update ZV_ZLG_T
      set    MUT_VON = F2QAPENV.F_TrxDate, MUT_BIS = ALQCNST0.CMAX_DAT, KD_LNR_VIS = F2QAPENV.F_SU, STAT_CD = decode(STAT_CD,null,100,STAT_CD), VERS_VOR = mod(VERS_VOR + 1,100000),
             VIS_STAT_CD_CASH = 30
      where  AUF_LNR         = P_AufLnr
      and    ZLG_STAT_CD     = 901
      and    USERBK_NR       = V_Ub
      and    VERS            = ALQCNST0.CVERS_HIGH_VALUE;

   exception
      when others then
         ZVQF_LOG.ErrorLog(null);
         raise;

   end Verarbeitung;
   --------------------------------------------------------------------

   --------------------------------------------------------------------
   /**
      Schliessen von allfällig noch offenen package-weiten Cursorn.

      P_SrJobNr   =>
      P_WfGfLnr   =>
      P_WfMailLnr =>
      P_Stat      =>

      return      =>
   */
   function  F_VerarbEnd(P_SrJobNr     in     number,
                         P_WfGfLnr     in     number,
                         P_WfMailLnr   in     varchar2,
                         P_Stat        in     number)
                         return number is

   L_Commit        number := 0;                                     -- 0 = kein commit auführen
   L_Stat          number;
   L_String        varchar2(100);
   L_LogLnr        number;
   L_WfActCd       number := 1;                                     -- Terminate Case
   L_WfErrMsg      varchar2(200);
   L_WfTyp         varchar2(1) := 'G';                              -- G= kein commit WfStep

   begin
      if P_Stat = ALQCNST0.CRS_OK then
         SRQJDMN0.SetDone(P_SrJobNr, null, L_Commit);               -- alle SR_JOB_ACT auf erledigt(3) setzen
      else
         rollback; F2QAPENV.TrxInit;
         SRQWKFL0.WorkflowError(L_WfTyp, P_WfGfLnr, P_WfMailLnr,
            null, L_WfErrMsg, L_LogLnr, L_WfActCd, L_Stat);
         if L_Stat <> ALQCNST0.CRS_OK then
            rollback; F2QAPENV.TrxInit;
         end if;
         SRQJDMN0.SetVStat(P_SrJobNr, 2, 101, null, null, null, L_String); -- SR_JOB_ACT auf fehlerhaft(101) setzen (mit commit)
      end if;
      commit; F2QAPENV.TrxInit;
      return P_Stat;

   exception
      when others then
         ZVQF_LOG.ErrorLog(null);
         return ALQCNST0.CRS_FAILED;                                        -- Fehlerausgang

   end F_VerarbEnd;
   --------------------------------------------------------------------

   --------------------------------------------------------------------
   /**
      Returns the revision of the package

      return   =>
   */
   function F_Revision
            return char is

   begin
       return('$Revision$');
   end F_Revision;
--------------------------------------------------------------------

begin
   null;                                                            -- there is no initial activity
end ZVQCASA0;
/
show err
