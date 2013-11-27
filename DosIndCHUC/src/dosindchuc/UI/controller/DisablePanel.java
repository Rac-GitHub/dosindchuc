/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.controller;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ir
 */

/*
 *  The DisablePanel will simulate the usage of a "Glass Pane" except that the
 *  glass pane effect is only for the container and not the entire frame.
 *  By default the glass pane is invisible. When the DisablePanel is disabled
 *  then the glass pane is made visible. In addition:
 *
 *  a) All MouseEvents will now be intercepted by the glass pane.
 *  b) The panel is removed from the normal focus traveral policy to prevent
 *     any component on the panel from receiving focus.
 *  c) Key Bindings for any component on the panel will be intercepted.
 *
 *  Therefore, the panel and components will behave as though they are disabled
 *  even though the state of each component has not been changed.
 *
 *  The background color of the glass pane should use a color with an
 *  alpha value to create the disabled look.
 *
 *  The other approach to disabling components on a Container is to recurse
 *  through all the components and set each individual component disabled.
 *  This class supports two static methods to support this functionality:
 *
 *  a) disable( Container ) - to disable all Components on the Container
 *  b) enable ( Container ) - to enable all Components disabled by the
 *     disable() method. That is any component that was disabled prior to using
 *     the disable() method method will remain disabled.
 */

public class DisablePanel extends JPanel {
    
    
	private static Map<Container, List<JComponent>> containers = new HashMap<>();



	/**
	 *  Convenience static method to disable all components of a given
	 *  Container, including nested Containers.
	 *
	 *  @param container the Container containing Components to be disabled
	 */
	public void disable(Container container)
	{
		List<JComponent> components = (List<JComponent>) SwingUtilities.getAncestorOfClass(JComponent.class, container); 
                // getDescendantsOfType(JComponent.class, container, true);
		List<JComponent> enabledComponents = new ArrayList<>();
		
                containers.put(container, enabledComponents);

		for (JComponent component: components)
		{
			if (component.isEnabled())
			{
				enabledComponents.add( component );
				component.setEnabled(false);
			}
		}
	}

        
        
	/**
	 *  Convenience static method to enable Components disabled by using
	 *  the disable() method. Only Components disable by the disable()
	 *  method will be enabled.
	 *
	 *  @param container a Container that has been previously disabled.
	 */
	public void enable(Container container)
	{
		List<JComponent> enabledComponents = containers.get( container );

		if (enabledComponents != null)
		{
			for (JComponent component: enabledComponents)
			{
				component.setEnabled(true);
			}

			containers.remove( container );
		}
	}

        
        
 

   
}
