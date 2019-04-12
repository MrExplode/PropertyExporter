package io.github.mrexplode.propertyexporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="export")
public class ExporterMojo extends AbstractMojo {
    
    /**
     * The properties to export
     */
    @Parameter(property="export.propList")
    private String[] propList;
    
    /**
     * The file, where the properties will be written
     */
    @Parameter(property="export.file")
    private File file;
    
    /**
     * The separator between the values
     */
    @Parameter(property="export.separator", defaultValue=";")
    private String separator;
    
    /**
     * Force create the specified file's parent directory.
     */
    @Parameter(property="export.forceMakeDir", defaultValue="false")
    private boolean forceMakeDir;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Log logger = getLog();
        try {
            if (!file.exists()) {
                logger.info("Creating Property file...");
                if (forceMakeDir) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < propList.length; i++) {
                String prop = propList[i];
                logger.info("Writing property: " + (i + 1 == propList.length ? "" : separator));
                writer.print("\\" + prop + separator);
            }
            writer.close();
            logger.info("Properties exported!");
        } catch (IOException e) {
            throw new MojoFailureException("Failed IO operation", e);
        }
    }

}
