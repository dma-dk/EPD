/* Copyright (c) 2011 Danish Maritime Authority
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package dk.dma.epd.shore.settings.gui;

import dk.dma.epd.common.prototype.settings.layers.ENCLayerCommonSettings;

/**
 * Maintains shore specific ENC layer settings.
 * 
 * @author Janus Varmarken
 */
public class ENCLayerSettings extends ENCLayerCommonSettings<ENCLayerSettings.IObserver> {

    /**
     * Used internally to check if new map windows should try to make dongle
     * check - if no dongle, don't retry on every new map.
     */
    private boolean encSuccess = true;

    /**
     * Used internally to check if new map windows should try to make dongle
     * check - if no dongle, don't retry on every new map.
     * 
     * @return {@code true} if a new map window should attempt to make a dongle
     *         check, {@code false} otherwise.
     */
    public boolean isEncSuccess() {
        try {
            this.settingLock.readLock().lock();
            return this.encSuccess;
        } finally {
            this.settingLock.readLock().unlock();
        }
    }

    /**
     * Used internally to check if new map windows should try to make dongle
     * check - if no dongle, don't retry on every new map.
     * 
     * @param encSuccess
     *            {@code true} if a new map window should attempt to make a
     *            dongle check, {@code false} otherwise.
     */
    public void setEncSuccess(final boolean encSuccess) {
        try {
            this.settingLock.writeLock().lock();
            if (this.encSuccess == encSuccess) {
                // No change, no need to notify observers.
                return;
            }
            // There was a change, update and notify observers.
            this.encSuccess = encSuccess;
            for (IObserver obs : this.observers) {
                obs.encSuccessChanged(encSuccess);
            }
        } finally {
            this.settingLock.writeLock().unlock();
        }
    }

    /**
     * Interface for observing an {@link ENCLayerSettings} for changes.
     * 
     * @author Janus Varmarken
     * 
     */
    public interface IObserver extends ENCLayerCommonSettings.IObserver {

        /**
         * Invoked when {@link ENCLayerSettings#isEncSuccess()} has changed.
         * 
         * @param encSuccess
         *            See return value of
         *            {@link ENCLayerSettings#isEncSuccess()}.
         */
        void encSuccessChanged(boolean encSuccess);

    }
}