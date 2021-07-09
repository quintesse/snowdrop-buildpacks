package dev.snowdrop.buildpack.utils;

import dev.snowdrop.buildpack.model.BuildPlan;
import dev.snowdrop.buildpack.model.BuildPlanProvide;
import dev.snowdrop.buildpack.model.BuildPlanRequire;
import org.jboss.logging.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class TomlHandler {
    static final org.jboss.logging.Logger LOG = Logger.getLogger(TomlHandler.class);

    public static void writeBuildPlan(BuildPlan buildPlan) {
        StringBuilder str = new StringBuilder();

        if (buildPlan.getBuildPlanProvides() != null) {
            for(BuildPlanProvide bpp: buildPlan.getBuildPlanProvides()) {
                str.append(bpp.toArray());
            }
        }

        if (buildPlan.getBuildPlanRequires() != null) {
            for (BuildPlanRequire bpr : buildPlan.getBuildPlanRequires()) {
                str.append(bpr.toArray());
            }
        }

        LOG.info("Build Plan generated: " + str.toString());

        try {
            FileWriter myWriter = new FileWriter(buildPlan.getPath());
            myWriter.write(str.toString());
            myWriter.close();
        } catch (IOException e) {
            LOG.info("Error occurred !!");
            LOG.info(e.getMessage());
        }
    }
}
