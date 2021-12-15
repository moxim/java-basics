package ch.finnova.java.schulung.temperature;

public class CelsiusToFahrenheitConverter extends TemperatureConverter {
    @Override
    public float convert(float celsius) {
        return celsius * 9 / 5 + 32; // T(°F) = T(°C) × 9/5 + 32
    }
}
