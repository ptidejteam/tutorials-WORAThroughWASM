// From https://www.baeldung.com/java-teavm
package net.ptidej.teavm.example3;

import org.teavm.jso.JSBody;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

public class Client {
	public static void main(final String[] args) {
		final HTMLDocument document = HTMLDocument.current();

		final HTMLElement h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("TeaVM Example"));
		document.getBody().appendChild(h1);

		final HTMLElement div = document.createElement("div");
		div.setAttribute("id", "calculator-container");
		document.getBody().appendChild(div);

		// Get the div element created above
		// Could be from an existing HTML document
		final HTMLElement container = document
				.getElementById("calculator-container");

		// Create input fields
		final HTMLInputElement input1 = (HTMLInputElement) document
				.createElement("input");
		input1.setType("number");
		container.appendChild(input1);

		final HTMLInputElement input2 = (HTMLInputElement) document
				.createElement("input");
		input2.setType("number");
		container.appendChild(input2);

		// Create a button
		final HTMLButtonElement button = (HTMLButtonElement) document
				.createElement("button");
		button.appendChild(document.createTextNode("Calculate Sum"));
		container.appendChild(button);

		// Create a div to display the result
		final HTMLElement resultDiv = document.createElement("div");
		container.appendChild(resultDiv);

		// Add click event listener to the button
		button.onClick((evt) -> {
			try {
				final int num1 = Integer.parseInt(input1.getValue());
				final int num2 = Integer.parseInt(input2.getValue());
				final int mul = Client.mul(num1, num2);
				resultDiv.setTextContent("Result: " + mul);
			}
			catch (final NumberFormatException e) {
				resultDiv.setTextContent("Please enter valid integer numbers.");
			}
		});
	}

	@JSBody(params = { "v1", "v2" }, script = "return v1 * v2;")
	public static native int mul(final int l1, final int l2);
}