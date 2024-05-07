package ar.edu.unju.fi.ejercicio6.main;
import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

    public static void main(String[] args) {
        FelinoDomestico gato = new FelinoDomestico("Tanner", (byte)20, 186);

        Converter<FelinoDomestico, FelinoSalvaje> converter = x -> {
            FelinoSalvaje felinoSalvaje = new FelinoSalvaje(x.getNombre(), x.getEdad(), x.getPeso());
            return felinoSalvaje;
        };

        FelinoSalvaje felino1 = converter.convert(gato);

        converter.mostrarObjeto(felino1);
    }

}
