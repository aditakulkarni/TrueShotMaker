package org.opencv.android;

import java.io.FileNotFoundException;

/**
 * Installation callback interface.
 */
public interface InstallCallbackInterface
{
    /**
     * New package installation is required.
     */
    static final int NEW_INSTALLATION = 0;
    /**
     * Current package installation is in progress.
     */
    static final int INSTALLATION_PROGRESS = 1;

    /**
     * Target package name.
     * @return Return target package name.
     */
    public String getPackageName();
    /**
     * Installation is approved.
     */
    public void install() throws FileNotFoundException;
    /**
     * Installation is canceled.
     */
    public void cancel() throws FileNotFoundException;
    /**
     * Wait for package installation.
     */
    public void wait_install() throws FileNotFoundException;
};
