package org.viking.bankett;

import org.viking.bankett.valhalla.GatesOfValhalla;
import org.viking.bankett.valhalla.LateViking;
import org.viking.bankett.valhalla.Viking;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public class VikingGenerator {
    private final @Nonnull Chieftain chieftain;
    private final @Nonnull GatesOfValhalla gates;

    /**
     * Cria um gerado de Vikings. Os Vikings gerados irão interagir com o {@link Chieftain} e
     * com o {@link GatesOfValhalla} fornecidos.
     *
     * @param chieftain O chefe da horda, que imporá ordem e sincronização
     * @param gates Canal de comunicação com Valhalla, por meio do qual o guerreiro Viking
     *              pode enviar suas preces
     */
    public VikingGenerator(@Nonnull Chieftain chieftain, @Nonnull GatesOfValhalla gates) {
        this.chieftain = chieftain;
        this.gates = gates;
    }

    /**
     * Cria um Viking e inicia sua execução paralela. O método retorna assim que
     * execução paralela do Viking estiver garantida. Esse método não espera o término
     * da execução do Viking.
     *
     * @param late true se, e somente se, o Viking a ser criado está atrasado. Vikings
     *             atrasados chegam após o fim do banquete, e por isso imediatamente
     *             rezam, sem tentar se sentar à mesa.
     * @param berserker true se, e somente se, o Viking a ser criado é um berserker.
     *                  Berserkers são vikings extremamente violentos e imprevisíveis,
     *                  semeando dor e sofrimento por onde passam. Eles irão seguir as
     *                  regras do banquete e das preces, mas os vikings normais tem medo deles.
     */
    public void spawnViking(boolean late, boolean berserker) {
        Viking viking = late ? new LateViking(chieftain, gates, berserker)
                             : new Viking(chieftain, gates, berserker);
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

    /**
     * Espera até que todos os Vikings criados por spawnViking() tenham terminado sua execução.
     */
    public void stop() {
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

}
