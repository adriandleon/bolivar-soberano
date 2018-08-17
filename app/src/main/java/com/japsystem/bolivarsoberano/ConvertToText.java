package com.japsystem.bolivarsoberano;

public class ConvertToText {

    private static int flag = 0;
    private static int numero;
    public String importe_parcial;
    private static String num;
    private static String num_letra;
    private static String num_letram;
    private static String num_letradm;
    private static String num_letracm;
    private static String num_letramm;
    private static String num_letradmm;
    private static String num_letracmm;

    private static String unidad(int numero) {

        switch (numero) {
            case 9:
                num = "nueve";
                break;
            case 8:
                num = "ocho";
                break;
            case 7:
                num = "siete";
                break;
            case 6:
                num = "seis";
                break;
            case 5:
                num = "cinco";
                break;
            case 4:
                num = "cuatro";
                break;
            case 3:
                num = "tres";
                break;
            case 2:
                num = "dos";
                break;
            case 1:
                if (flag == 0)
                    num = "un";
                else
                    num = "un";
                break;
            case 0:
                num = "";
                break;
        }
        return num;
    }

    private static String decena(int numero) {

        if (numero >= 90 && numero <= 99) {
            num_letra = "noventa ";
            if (numero > 90)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 90));
        } else if (numero >= 80 && numero <= 89) {
            num_letra = "ochenta ";
            if (numero > 80)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 80));
        } else if (numero >= 70 && numero <= 79) {
            num_letra = "setenta ";
            if (numero > 70)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 70));
        } else if (numero >= 60 && numero <= 69) {
            num_letra = "sesenta ";
            if (numero > 60)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 60));
        } else if (numero >= 50 && numero <= 59) {
            num_letra = "cincuenta ";
            if (numero > 50)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 50));
        } else if (numero >= 40 && numero <= 49) {
            num_letra = "cuarenta ";
            if (numero > 40)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 40));
        } else if (numero >= 30 && numero <= 39) {
            num_letra = "treinta ";
            if (numero > 30)
                num_letra = num_letra.concat("y ").concat(unidad(numero - 30));
        } else if (numero >= 20 && numero <= 29) {
            if (numero == 20)
                num_letra = "veinte ";
            else
                num_letra = "veinti".concat(unidad(numero - 20));
        } else if (numero >= 10 && numero <= 19) {
            switch (numero) {
                case 10:

                    num_letra = "diez ";
                    break;

                case 11:

                    num_letra = "once ";
                    break;

                case 12:

                    num_letra = "doce ";
                    break;

                case 13:

                    num_letra = "trece ";
                    break;

                case 14:

                    num_letra = "catorce ";
                    break;

                case 15:

                    num_letra = "quince ";
                    break;

                case 16:

                    num_letra = "dieciseis ";
                    break;

                case 17:

                    num_letra = "diecisiete ";
                    break;

                case 18:

                    num_letra = "dieciocho ";
                    break;

                case 19:

                    num_letra = "diecinueve ";
                    break;

            }
        } else
            num_letra = unidad(numero);

        return num_letra;
    }

    private static String centena(int numero) {
        if (numero >= 100) {
            if (numero >= 900 && numero <= 999) {
                num_letra = "novecientos ";
                if (numero > 900)
                    num_letra = num_letra.concat(decena(numero - 900));
            } else if (numero >= 800 && numero <= 899) {
                num_letra = "ochocientos ";
                if (numero > 800)
                    num_letra = num_letra.concat(decena(numero - 800));
            } else if (numero >= 700 && numero <= 799) {
                num_letra = "setecientos ";
                if (numero > 700)
                    num_letra = num_letra.concat(decena(numero - 700));
            } else if (numero >= 600 && numero <= 699) {
                num_letra = "seiscientos ";
                if (numero > 600)
                    num_letra = num_letra.concat(decena(numero - 600));
            } else if (numero >= 500 && numero <= 599) {
                num_letra = "quinientos ";
                if (numero > 500)
                    num_letra = num_letra.concat(decena(numero - 500));
            } else if (numero >= 400 && numero <= 499) {
                num_letra = "cuatrocientos ";
                if (numero > 400)
                    num_letra = num_letra.concat(decena(numero - 400));
            } else if (numero >= 300 && numero <= 399) {
                num_letra = "trescientos ";
                if (numero > 300)
                    num_letra = num_letra.concat(decena(numero - 300));
            } else if (numero >= 200 && numero <= 299) {
                num_letra = "doscientos ";
                if (numero > 200)
                    num_letra = num_letra.concat(decena(numero - 200));
            } else if (numero >= 100 && numero <= 199) {
                if (numero == 100)
                    num_letra = "cien ";
                else
                    num_letra = "ciento ".concat(decena(numero - 100));
            }
        } else
            num_letra = decena(numero);

        return num_letra;
    }

    private static String miles(int numero) {
        if (numero >= 1000 && numero < 2000) {
            num_letram = ("mil ").concat(centena(numero % 1000));
        }
        if (numero >= 2000 && numero < 10000) {
            flag = 1;
            num_letram = unidad(numero / 1000).concat(" mil ").concat(centena(numero % 1000));
        }
        if (numero < 1000)
            num_letram = centena(numero);

        return num_letram;
    }

    private static String decmiles(int numero) {
        if (numero == 10000)
            num_letradm = "diez mil";
        if (numero > 10000 && numero < 20000) {
            flag = 1;
            num_letradm = decena(numero / 1000).concat("mil ").concat(centena(numero % 1000));
        }
        if (numero >= 20000 && numero < 100000) {
            flag = 1;
            num_letradm = decena(numero / 1000).concat(" mil ").concat(miles(numero % 1000));
        }


        if (numero < 10000)
            num_letradm = miles(numero);

        return num_letradm;
    }

    private static String cienmiles(int numero) {
        if (numero == 100000)
            num_letracm = "cien mil";
        if (numero >= 100000 && numero < 1000000) {
            flag = 1;
            num_letracm = centena(numero / 1000).concat(" mil ").concat(centena(numero % 1000));
        }
        if (numero < 100000)
            num_letracm = decmiles(numero);
        return num_letracm;
    }

    private static String millon(int number) {
        if (number >= 1000000 && number < 2000000) {
            flag = 1;
            num_letramm = ("un millón ").concat(cienmiles(number % 1000000));
        }
        if (number >= 2000000 && number < 10000000) {
            flag = 1;
            num_letramm = unidad(number / 1000000).concat(" millones ").concat(cienmiles(number % 1000000));
        }
        if (number < 1000000)
            num_letramm = cienmiles(number);

        return num_letramm;
    }

    private static String decmillon(int number) {
        if (number == 10000000)
            num_letradmm = "diez millones";
        if (number > 10000000 && number < 20000000) {
            flag = 1;
            num_letradmm = decena(number / 1000000).concat("millones ").concat(cienmiles(number % 1000000));
        }
        if (number >= 20000000 && number < 100000000) {
            flag = 1;
            num_letradmm = decena(number / 1000000).concat(" milllones ").concat(millon(number % 1000000));
        }

        if (number < 10000000)
            num_letradmm = millon(number);

        return num_letradmm;
    }

    private static String cienmillones(int number) {

        if (number == 100_000_000)
            num_letracmm = "cien millones";
        if (number > 100_000_000 && number < 200_000_000) {
            flag = 1;
            num_letracmm = centena(number / 1_000_000).concat("millones ").concat(cienmiles(number % 1_000_000));
        }
        if (number >= 200_000_000 && number < 1_000_000_000) {
            flag = 1;
            num_letracmm = centena(number / 1_000_000).concat(" milllones ").concat(millon(number % 1_000_000));
        }

        if (number < 100_000_000)
            num_letracmm = decmillon(number);

        return num_letracmm;
    }

    public static String convertirLetras(int numero) {
        return cienmillones(numero);
    }
}
