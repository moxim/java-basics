package ch.finnova.java.schulung.temperature;

public abstract class TemperatureConverter {
    public abstract float convert(float temperature);

    public static void main(String[] args) {
        TemperatureConverter converter = new CelsiusToFahrenheitConverter();
        float fahrenheit = converter.convert(42);
        converter = new KelvinToCelsiusConverter();
        float celsius = converter.convert(42);
        converter = new FahrenheitToCelsiusConverter();
        celsius = converter.convert(42);
    }
}
