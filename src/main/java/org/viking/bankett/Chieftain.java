package org.viking.bankett;

import org.viking.bankett.valhalla.God;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("WeakerAccess")
public class Chieftain {
    /**
     * Cria um {@link Chieftain}, o chefe da horda.
     *
     * Essa instância controlará uma horda com inicialmente hordeSize guerreiros e uma mesa
     * com tableSize cadeiras.
     *
     *
     * @param tableSize Número de cadeiras na mesa. No máximo tableSize vikings podem estar
     *                  sentados à mesa
     * @param hordeSize Número de vikings esperados para o banquete
     */
    public Chieftain(int tableSize, int hordeSize) {
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

    /**
     * Adquire uma cadeira e dois pratos de comida
     *
     * @param berserker true se, e somente se, o Viking que chama esse método é um Berserker.
     *                  Por razões de segurança, guerreiros normais e Berserkers jamais podem
     *                  ser atribuídos pelo {@link Chieftain} à cadeiras adjacentes.
     * @return Índice da cadeira, como um número no intervalo [0, tableSize)
     */
    public int acquireSeatAndPlates(boolean berserker) {
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

    /**
     * Libera uma cadeira préviamente adquirida via acquireSeatAndPlates().
     *
     * @param pos índice da cadeira ocupada pelo guerreiro
     */
    public synchronized void releaseSeatAndPlates(int pos) {
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

    /**
     * Indica ao guerreiro para qual deus viking ele deve fazer suas preces. O deus deve ser
     * escolhido aleatóriamente, mas de forma a respeitar as regras dispostas nas escrituras
     * (enunciado do T2).
     *
     * @return O Deus atribuído ao guerreiro.
     */
    public synchronized God getGod() {
        throw new UnsupportedOperationException("Me implemente!"); //FIXME implementar!
    }

}
