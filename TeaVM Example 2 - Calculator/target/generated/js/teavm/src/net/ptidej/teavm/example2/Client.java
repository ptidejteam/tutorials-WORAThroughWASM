// From https://www.baeldung.com/java-teavm
package net.ptidej.teavm.example2;

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
		button.addEventListener("click", (evt) -> {
			try {
				final long num1 = Long.parseLong(input1.getValue());
				final long num2 = Long.parseLong(input2.getValue());
				final long sum = num1 + num2;
				resultDiv.setTextContent("Result: " + sum);
			}
			catch (final NumberFormatException e) {
				resultDiv.setTextContent("Please enter valid integer numbers.");
			}
		});
	}
}
