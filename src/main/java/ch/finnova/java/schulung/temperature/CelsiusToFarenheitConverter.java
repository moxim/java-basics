package ch.finnova.java.schulung.temperature;

public class CelsiusToFarenheitConverter extends TemperatureConverter {
    @Override
    public float convert(float celsius) {
        return celsius * 9 / 5 + 32;
    }
}
