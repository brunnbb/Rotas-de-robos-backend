package com.jmc.entities;

import com.jmc.enums.Face;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Representa o Robô responsável por carregar pacotes, navegar até as prateleiras e estações de descarregamento.
 * Cada robô possui um identificador.
 * Os atributos carrying, packageId e postGoal não são utilizados na versão atual do projeto.
 * Eles serão necessários se uma futura implementação multiagente ocorrer
 */
public class Robot {
    private int id;
    private boolean carrying;
    private int packageId;
    private boolean postGoal;
    private List<Block> pathToUnloadingStation;

    public Robot(int id) {
        this.id = id;
        this.carrying = false;
        this.packageId = -1;
        this.postGoal = false;
        this.pathToUnloadingStation = new ArrayList<Block>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public boolean isPostGoal() {
        return postGoal;
    }

    public void setPostGoal(boolean postGoal) {
        this.postGoal = postGoal;
    }

    /**
     * Move o robô até a prateleira especificada dentro do grid do armazém.
     *
     * @param warehouseGrid matriz que representa o armazém.
     * @param shelf {@link Block} bloco da prateleira para onde o robô deve ir.
     */
    public void goToShelf(Block[][] warehouseGrid, Block shelf) {
        warehouseGrid[shelf.getI()][shelf.getJ()].setRobot(this);
    }

    /**
     * Calcula e retorna o caminho do robô até a estação de descarregamento, partindo da prateleira fornecida.
     *
     * @param warehouseGrid matriz que representa o armazém.
     * @param shelf {@link Block} bloco da prateleira de origem.
     * @return {@link List<Block>} lista de blocos representando o caminho até a estação.
     */
    public List<Block> goToUnloadingStation(Block[][] warehouseGrid, Block shelf) {
        pathToUnloadingStation = new ArrayList<>();
        pathToUnloadingStation.add(shelf);

        int i = shelf.getI();
        int j = shelf.getJ();

        for (Map.Entry<Face, Boolean> entry : shelf.getDirections().entrySet()) {
            if (entry.getKey() == Face.EAST && entry.getValue()) {
                pathToUnloadingStation.add(warehouseGrid[i][j + 1]);
            } else if (entry.getKey() == Face.WEST && entry.getValue()) {
                pathToUnloadingStation.add(warehouseGrid[i][j - 1]);
            }
        }

        i = pathToUnloadingStation.getLast().getI();
        j = pathToUnloadingStation.getLast().getJ();

        while (i < 11) {
            pathToUnloadingStation.add(warehouseGrid[i + 1][j]);
            i++;
        }

        while (j < 14) {
            pathToUnloadingStation.add(warehouseGrid[i][j + 1]);
            j++;
        }
        return pathToUnloadingStation;
    }

    /**
     * Retorna o caminho que volta à prateleira de origem.
     *
     * @param warehouseGrid matriz que representa o armazém.
     * @param shelf {@link Block} bloco da prateleira de destino.
     * @return {@link List<Block>} caminho invertido até a prateleira
     *
     * @deprecated o retorno à prateleira agora é tratado de maneira diferente.
     */
    @Deprecated
    public List<Block> returnToShelf(Block[][] warehouseGrid, Block shelf) {
        List<Block> pathToReturnToShelf = new ArrayList<>(this.pathToUnloadingStation);
        Collections.reverse(pathToReturnToShelf);
        return pathToReturnToShelf;
    }

    @Override
    public String toString() {
        return "R" + id;
    }
}
