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
package dk.dma.epd.shore.gui.notification;

import javax.swing.ImageIcon;

import dk.dma.epd.common.prototype.gui.notification.NotificationDetailPanel;
import dk.dma.epd.common.prototype.gui.notification.NotificationPanel;
import dk.dma.epd.common.prototype.gui.notification.NotificationTableModel;
import dk.dma.epd.common.prototype.notification.NotificationType;
import dk.dma.epd.common.prototype.notification.Notification.NotificationSeverity;
import dk.dma.epd.common.text.Formatter;

/**
 * A strategic route implementation of the {@linkplain NotificationPanel} class
 */
public class StrategicRouteNotificationPanel extends NotificationPanel<StrategicRouteNotification> {

    private static final long serialVersionUID = 1L;
    
    private static final String[] NAMES = {
        "", "", "Date", "Title"
    };

    /**
     * Constructor
     */
    public StrategicRouteNotificationPanel() {
        super();
        
        table.getColumnModel().getColumn(0).setMaxWidth(18);
        table.getColumnModel().getColumn(1).setMaxWidth(18);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationType getNotitficationType() {
        return NotificationType.STRATEGIC_ROUTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NotificationTableModel<StrategicRouteNotification> initTableModel() {
        return new NotificationTableModel<StrategicRouteNotification>() {
            private static final long serialVersionUID = 1L;
            
            @Override 
            public String[] getColumnNames() { 
                return NAMES; 
            }
            
            @Override 
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex <= 1) {
                    return ImageIcon.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }
            
            @Override 
            public Object getValueAt(int rowIndex, int columnIndex) {
                StrategicRouteNotification notification = getNotification(rowIndex);
                
                switch (columnIndex) {
                case 0: return !notification.isRead() 
                                ? ICON_UNREAD 
                                : (notification.isAcknowledged() ? ICON_ACKNOWLEDGED : null);
                case 1: return notification.getSeverity() == NotificationSeverity.ALERT
                                ? ICON_ALERT
                                : (notification.getSeverity() == NotificationSeverity.WARNING ? ICON_WARNING : null);
                case 2: return Formatter.formatShortDateTime(notification.getDate());
                case 3: return notification.getTitle();
                default:
                }
                return null; 
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NotificationDetailPanel<StrategicRouteNotification> initNotificationDetailPanel() {
        NotificationDetailPanel<StrategicRouteNotification> panel = new NotificationDetailPanel<StrategicRouteNotification>();
        return panel;
    }
    
    
}
