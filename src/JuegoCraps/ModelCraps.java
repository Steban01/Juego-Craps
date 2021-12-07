package JuegoCraps;

/**
 * ModelCraps apply craps rules
 * estado = 1 Natural Winner
 * estado = 2 Craps Looser
 * estado = 3 Stablish Punto
 * estado = 4 Point Winner
 * estado = 5 Point Looser
 *
 * @author Esteban Andres Hernandez Cortes-esteban.cortes@correounivalle.edu.co
 * @version V1.0.0 date:06/12/2021
 */
public class ModelCraps {
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String[] estadotoString;
    private int[] caras;

    /**
     * Class Constructor
     */
    public ModelCraps() {
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        estadotoString = new String[2];
        flag = 0;
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void CalcularTiro() {
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Establish game state according to estado atribute value
     * estado = 1 Natural Winner
     * stado = 2 Craps Looser
     * estado = 3 Stablish Punto
     */
    public void determinarJuego() {
        if (flag == 0) {
            if (tiro == 7 || tiro == 11) {
                estado = 1;
            } else {
                if (tiro == 3 || tiro == 2 || tiro == 12) {
                    estado = 2;
                } else {
                    estado = 3;
                    punto = tiro;
                    flag = 1;

                }

            }

        } else {
            rondaPunto();

        }

    }

    /**
     * Establish game state according to estado atribute value
     * estado = 4 Point Winner
     * estado = 5 Point Looser
     */
    private void rondaPunto() {
        if (tiro == punto) {
            estado = 4;
            flag = 0;
        } else {
            if (tiro == 7) {
                estado = 5;
                flag = 0;

            } else {
                estado = 6;
            }
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado atribute value
     *
     * @return Message for the View Class
     */
    public String[] getEstadotoString() {
        switch (estado) {
            case 1:
                estadotoString[0] = "Tiro de salida=" + tiro + "";
                estadotoString[1] = "Sacaste un natural, GANASTE";
                break;
            case 2:
                estadotoString[0] = "Tiro de salida=" + tiro + "";
                estadotoString[1] = "Sacaste un craps, PERDISTE";
                break;
            case 3:
                estadotoString[0] = "Tiro de salida=" + tiro + "\nPunto=" + punto;
                estadotoString[1] = "Estableciste punto " + punto + " aun no has ganado, debes seguir lanzando \n pero si sacas 7 antes que " + punto + " perderas";
                break;
            case 4:
                estadotoString[0] = "Tiro de salida=" + punto + "\nPunto=" + punto + "\nValor del nuevo tiro=" + tiro;
                estadotoString[1] = "Volviste a sacar " + punto + " ,GANASTE";
                break;
            case 5:
                estadotoString[0] = "Tiro de salida=" + punto + "\nPunto=" + punto + "\nValor del nuevo tiro=" + tiro;
                estadotoString[1] = "Sacaste 7 antes que " + punto + " ,PERDISTE";
                break;
            case 6:
                estadotoString[0] = "Tiro de salida=" + punto + "\nPunto=" + punto + "\nValor del nuevo tiro=" + tiro;
                estadotoString[1] = "Estas en punto, aun no has ganado, debes seguir lanzando \n pero si sacas 7 antes que " + punto + " perderas";

        }
        return estadotoString;
    }

    public int[] getCaras() {
        return caras;
    }
}
