package com.mercadolibre.quasaroperation.util;

import com.lemmingapex.trilateration.LinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.mercadolibre.quasaroperation.configuration.ActiveSatelliteConfig;
import com.mercadolibre.quasaroperation.exception.LocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.awt.Point;

/** LEVEL 1 **/

@Component
public class QuasarUtils {
    @Autowired
    private ActiveSatelliteConfig activeSatelite;

    /**
     * // input: distancia al emisor tal cual se recibe en cada satélite
     * // output: las coordenadas ‘x’ e ‘y’ del emisor del mensaje
     **/
    public Point getLocation(double[] distances) {

        var satelliteCoordinate = new double[][]{
                {activeSatelite.KENOBI_POINT_X, activeSatelite.KENOBI_POINT_Y},
                {activeSatelite.SKYWALKER_POINT_X, activeSatelite.SKYWALKER_POINT_Y},
                {activeSatelite.SATO_POINT_X, activeSatelite.SATO_POINT_Y}
        };

        if (distances.length > satelliteCoordinate.length) {
            throw new RuntimeException("La cantidad de distancia proporcionadas es superior a la cantidad de satelites activos.");

        }

        if (distances.length < satelliteCoordinate.length) {
            throw new RuntimeException("la informacion proporcionada es incompleta.");

        }

        LinearLeastSquaresSolver linearLeastSquaresSolver = new LinearLeastSquaresSolver(new TrilaterationFunction(satelliteCoordinate, distances));
        double[] emissorCoordinatesArray = linearLeastSquaresSolver.solve().toArray();

        return new Point((int) emissorCoordinatesArray[0], (int) emissorCoordinatesArray[1]);

    }

    /**
     * // input: el mensaje tal cual es recibido en cada satélite
     * // output: el mensaje tal cual lo genera el emisor del mensaje
     **/
    public String getMessage(String[][] messages) {
        try {
            var canPalabras = messages[0].length;

            String word = "";
            StringBuilder msgOriginal = new StringBuilder();

            for (int i = 0; i < canPalabras; i++) {
                for (String[] message : messages) {
                    if (i < message.length && !message[i].isEmpty() && !message[i].equals(word.trim())) {
                        msgOriginal.append(message[i]).append(" ");
                        word = message[i];
                    }
                }
            }

            return msgOriginal.toString().trim();
        } catch (Exception ex) {
            throw new RuntimeException("el mensaje no se pude decifrar por la siguiente razon: " + ex);
        }
    }
}
