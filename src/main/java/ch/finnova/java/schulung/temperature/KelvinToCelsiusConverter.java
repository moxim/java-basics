package ch.finnova.java.schulung.temperature;

public class KelvinToCelsiusConverter extends TemperatureConverter {
    @Override
    public float convert(float kelvin) {
        return kelvin - 273.15f; // T(°C) = T(K) - 273.15
    }
}
