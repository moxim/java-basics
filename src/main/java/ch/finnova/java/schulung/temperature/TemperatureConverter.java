package ch.finnova.java.schulung.temperature;

public abstract class TemperatureConverter {
    public abstract float convert(float temperature);

    public void m() {
        TemperatureConverter converter = new CelsiusToFarenheitConverter();
        float fahrenheit = converter.convert(42);
        converter = null;
    }
}
