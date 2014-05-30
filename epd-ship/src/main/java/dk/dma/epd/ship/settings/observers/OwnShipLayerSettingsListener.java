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
package dk.dma.epd.ship.settings.observers;

import dk.dma.epd.common.prototype.settings.observers.VesselLayerSettingsListener;
import dk.dma.epd.ship.settings.layers.OwnShipLayerSettings;

/**
 * Interface for observing an {@link OwnShipLayerSettings} for changes.
 * 
 * @author Janus Varmarken
 * 
 */
public interface OwnShipLayerSettingsListener extends
        VesselLayerSettingsListener {
    
    /**
     * Invoked when {@link OwnShipLayerSettings#isMultiSourcePntVisible()}
     * has changed.
     * 
     * @param msPntVisible
     *            {@code true} if multi source PNT should be displayed,
     *            {@code false} if multi source PNT should <i>not</i> be
     *            displayed.
     */
    void multiSourcePntVisibilityChanged(boolean msPntVisible);
}
