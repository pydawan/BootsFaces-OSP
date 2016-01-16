/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component.flyOutMenu;

import javax.faces.component.*;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:flyOutMenu /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.flyOutMenu.FlyOutMenu")
public class FlyOutMenuRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:flyOutMenu.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:flyOutMenu.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		FlyOutMenu flyOutMenu = (FlyOutMenu) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = flyOutMenu.getClientId();

		// put custom code here
		// Simple demo widget that simply renders every attribute value
		rw.startElement("ul", flyOutMenu);
		rw.writeAttribute("id", clientId, "id");

		Tooltip.generateTooltip(context, flyOutMenu, rw);

		String styleClass = flyOutMenu.getStyleClass();
		if (null == styleClass)
			styleClass = "dropdown-menu";
		else
			styleClass = "dropdown-menu " + flyOutMenu.getStyleClass();
		writeAttribute(rw, "class", styleClass);
		String width = flyOutMenu.getWidth();
		addUnitToWidthIfNecessary(width);
		String style = flyOutMenu.getStyle();
		if (null == style)
			style = "display: block; position: static; margin-bottom: 5px; *width: "+width;
		else
			style = "display: block; position: static; margin-bottom: 5px; *width: " + width +";" + flyOutMenu.getStyle();
		writeAttribute(rw, "style", style);
	}

	private void addUnitToWidthIfNecessary(String width) {
		try {
			Integer.parseInt(width);
			width += "px";
		} catch (NumberFormatException noError) {
		}
	}

	/**
	 * This methods generates the HTML code of the current b:flyOutMenu.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:flyOutMenu.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		FlyOutMenu flyOutMenu = (FlyOutMenu) component;
		ResponseWriter rw = context.getResponseWriter();
		rw.endElement("ul");
		Tooltip.activateTooltips(context, flyOutMenu);

	}

}