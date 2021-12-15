package ch.finnova.java.schulung.temperature;

public class FahrenheitToCelsiusConverter extends TemperatureConverter {
    @Override
    public float convert(float fahrenheit) {
        return (fahrenheit - 32) * 5 / 9; // T(°C) = (T(°F) - 32) × 5/9
    }
}
