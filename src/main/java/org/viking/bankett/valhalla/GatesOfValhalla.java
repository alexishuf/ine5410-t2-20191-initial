package org.viking.bankett.valhalla;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicInteger;

public class GatesOfValhalla {
    /* ********************************************************* *
     *               CUIDADO! CAUTION! FORSIKTIG!                *
     *                                                           *
     * Nenhum mortal alterou esse arquivo e tirou 10 no T2!      *
     * ********************************************************* */

    private long maxPrayMsecs;
    private double rivalToleranceRate = 0.05;
    private double superGodToleranceRate = 0.10;
    /**
     * Controla o número de preces por deus. Um {@link AtomicInteger} permite operações
     * concorrentes (como incrementos) consistentes sem necessidade de exclusão mútua.
     */
    private AtomicInteger[] prayers = new AtomicInteger[God.values().length];

    /**
     * Cria um canal de comunicação com o além.
     *
     * @param maxPrayMsecs Tempo máximo de uma prece, em milisegundos.
     */
    public GatesOfValhalla(long maxPrayMsecs) {
        this.maxPrayMsecs = maxPrayMsecs;
        for (int i = 0; i < God.values().length; i++)
            prayers[i] = new AtomicInteger(0);
    }

    public long getMaxPrayMsecs() {
        return maxPrayMsecs;
    }

    public void setMaxPrayMsecs(long maxPrayMsecs) {
        this.maxPrayMsecs = maxPrayMsecs;
    }

    public double getRivalToleranceRate() {
        return rivalToleranceRate;
    }

    public void setRivalToleranceRate(double rivalToleranceRate) {
        this.rivalToleranceRate = rivalToleranceRate;
    }

    public double getSuperGodToleranceRate() {
        return superGodToleranceRate;
    }

    public void setSuperGodToleranceRate(double superGodToleranceRate) {
        this.superGodToleranceRate = superGodToleranceRate;
    }

    /* ********************************************************* *
     *               CUIDADO! CAUTION! FORSIKTIG!                *
     *                                                           *
     * Nenhum mortal alterou esse arquivo e tirou 10 no T2!      *
     * ********************************************************* */

    /**
     * Reza para o deus indicado
     *
     * @param god o deus a receber a prece
     */
    public void pray(@Nonnull God god) {
        prayers[god.ordinal()].incrementAndGet();
        try {
            Thread.sleep((long) (Math.random()* getMaxPrayMsecs()));
        } catch (InterruptedException ignored) {}
    }

    /**
     * Mostra estatísticas sobre o número de preces por deus
     */
    public void showHistogram() {
        int max = 0, total = 0, totalNormal = 0;
        for (God god : God.values()) {
            int count = prayers[god.ordinal()].get();
            total += count;
            if (!god.isSuperGod())
                totalNormal += count;
            if (count > max) max = count;
        }
        for (God god : God.values()) {
            int count = prayers[god.ordinal()].get();
            char barChar = '#';
            String error = "";
            StringBuilder builder = new StringBuilder();
            for (God rival : god.getRivals()) {
                int his = prayers[rival.ordinal()].get();
                int maxAllowed = (int) Math.ceil(his * (1+rivalToleranceRate));
                int minAllowed = (int) Math.floor(his * (1-rivalToleranceRate));
                if (count < minAllowed) {
                    error = " [TOO FEW]";
                    barChar = '*';
                } else if (count > maxAllowed) {
                    error = " [TOO MUCH]";
                    barChar = '@';
                }
            }
            if (god.isSuperGod()) {
                if (count > Math.ceil(totalNormal * (1+superGodToleranceRate))) {
                    error = " [TOO MUCH]";
                    barChar = '@';
                }
            }
            for (int i = 0; i < 50*count/(double)max; i++)
                builder.append(barChar);
            System.out.printf("%5s |%s %d%s\n", god.name(), builder.toString(), count, error);
        }
        System.out.printf("Total: %d prayers!\n", total);
    }

    /* ********************************************************* *
     *               CUIDADO! CAUTION! FORSIKTIG!                *
     *                                                           *
     * Nenhum mortal alterou esse arquivo e tirou 10 no T2!      *
     * ********************************************************* */
}
