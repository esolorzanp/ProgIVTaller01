package model;

import util.Numbers;

public class FacturacionCobroConsumoVvienda {
    private int consumo;

    public FacturacionCobroConsumoVvienda(int consumo) {
        this.consumo = consumo;
    }

    public FacturacionCobroConsumoVvienda() {
        this.consumo = 0;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "FacturacionCobroConsumoVvienda = {" +
                "consumo=" + consumo +
                ", cobroFacturacionConsumo=" + this.getCobroFacturacionConsumo() +
                '}';
    }

    public int getCobroFacturacionConsumo() {
        int cargoFijo = 18000;
        int cargoConsumoLitros = 0;
        if (consumo <= 50)
            cargoConsumoLitros = cargoFijo;
        else if (consumo <= 200)
            cargoConsumoLitros = this.consumo * 2600;
        else // if (consumo > 200)
            cargoConsumoLitros = this.consumo * 2400;
        return cargoConsumoLitros;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            FacturacionCobroConsumoVvienda f = new FacturacionCobroConsumoVvienda();
            f.setConsumo(Numbers.calcAleatorio(1, 300));
            //System.out.println("Consumo vivienda en litros: " + f.getConsumo() + ", cobro: " + f.getCobroFacturacionConsumo());
            System.out.println(f.toString());
        }
    }
}
