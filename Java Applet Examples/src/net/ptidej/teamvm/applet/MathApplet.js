<script src=
"https://www.java.com/js/deployJava.js"></script>
<script>
var attributes = { id:'mathApplet', code:'net.ptidej.teavm.MathApplet', ...};
var parameters = { jnlp_href: 'math_applet.jnlp'} ;
deployJava.runApplet(attributes, parameters, '1.6');
</script>

<script language="javascript">
    function enterNums(){
        var numA = prompt('Enter number \'a\'?','0');
        var numB = prompt(
            'Enter number \'b\' (should be greater than number \'a\' ?','1');
        // set applet's public variable
        mathApplet.userName = "John Doe";

        // invoke public applet method
        var greeting = mathApplet.getGreeting();

        // get another class referenced by applet and
        // invoke its methods
        var calculator = mathApplet.getCalculator();
        calculator.setNums(numA, numB);

        // primitive datatype returned by applet
        var sum = calculator.add();

        // array returned by applet
        var numRange = calculator.getNumInRange();

        // check Java console log for this message
        mathApplet.printOut("Testing printing to System.out");

        // get another class, set static field and invoke its methods
        var dateHelper = mathApplet.getDateHelper();
        dateHelper.label = "Today\'s date is: ";
        var dateStr = dateHelper.getDate();
        <!-- ... -->
</script>