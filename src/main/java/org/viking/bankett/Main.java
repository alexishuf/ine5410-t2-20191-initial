package org.viking.bankett;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.viking.bankett.valhalla.GatesOfValhalla;

import java.io.PrintStream;

public class Main  {
    @Option(name = "--pray-time", aliases = {"-p"},
            usage = "Tempo máximo de uma prece, em millisegundos")
    private long prayMaxMsecs = 100;

    @Option(name = "--table-size", aliases = {"-t"},
            usage = "Tamanho da mesa, em cadeiras")
    private int tableSize = 50;

    @Option(name = "--horde-size", aliases = {"-s"},
            usage = "Tamanho da horda, em vikings")
    private int hordeSize = 1000;

    /* *********************************************************** *
     * Pode ser útil reativar esses 2 fields como opções de linha  *
     * de comando para testar sua solução                          *
     * *********************************************************** */

    @SuppressWarnings("FieldCanBeLocal")
//    @Option(name = "--rival-tol-rate", aliases = {"-r"}, usage = "Tolerance rate " +
//            "in prayers count difference between rival gods. Must be in the [0.0, 1.0) range.")
    private double rivalToleranceRate = 0.05;

    @SuppressWarnings("FieldCanBeLocal")
//    @Option(name = "--super-tol-rate", aliases = {"-g"}, usage = "Tolerance rate " +
//            "in prayers count difference between all normal gods and each super god. " +
//            "Must be in the [0.0, 1.0) range.")
    private double superGodToleranceRate = 0.10;

    @Option(name = "--help", aliases = {"-h"}, help = true)
    private boolean help = false;


    public static void main(String[] args) throws Exception {
        Main app = new Main();
        CmdLineParser parser = new CmdLineParser(app);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            showHelp(parser, System.err);
            throw e;
        }
        if (app.help) showHelp(parser, System.out);
        else app.run();
    }

    private static void showHelp(CmdLineParser parser, PrintStream out) {
        out.print("Uso: java -jar JARFILE ");
        parser.printSingleLineUsage(out);
        out.println();
        parser.printUsage(out);
    }

    private void run() {
        GatesOfValhalla gates = new GatesOfValhalla(prayMaxMsecs);
        gates.setRivalToleranceRate(rivalToleranceRate);
        gates.setSuperGodToleranceRate(superGodToleranceRate);
        Chieftain chieftain = new Chieftain(tableSize, hordeSize);
//        chieftain.setRivalToleranceRate(rivalToleranceRate);
//        chieftain.setSuperGodToleranceRate(superGodToleranceRate);
        VikingGenerator gen = new VikingGenerator(chieftain, gates);

        for (int i = 0; i < hordeSize; i++)
            gen.spawnViking(false, Math.random() < 0.5);

        try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {}

        for (int i = 0; i < hordeSize * (0.5 + Math.random()); i++)
            gen.spawnViking(true, Math.random() < 0.5);

        gen.stop();

        gates.showHistogram();
    }
}
