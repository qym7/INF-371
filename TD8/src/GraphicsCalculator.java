import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class GraphicsCalculator extends Application {
    Tokenizer tok = new Tokenizer();
    Label result;
    Label calcul;
    String process = "";


    @Override
    public void start(Stage stage) {
        HBox hresult = new HBox(1);
        VBox vresult = new VBox(1);
        stage.show();
        stage.setTitle("CALCULATOR!");
        stage.setHeight(250);
        stage.setWidth(200);

        calcul = new Label("");
        vresult.getChildren().add(calcul);
        result = new Label("0.");
        vresult.getChildren().add(result);


        hresult.getChildren().addAll(b('7'),b('8'),b('9'),b('+'));
        vresult.getChildren().add(hresult);

        hresult = new HBox(1);
        hresult.getChildren().addAll(b('4'),b('5'),b('6'),b('-'));
        vresult.getChildren().add(hresult);

        hresult = new HBox(1);
        hresult.getChildren().addAll(b('1'),b('2'),b('3'),b('*'));
        vresult.getChildren().add(hresult);

        hresult = new HBox(1);
        hresult.getChildren().addAll(b('0'),b('.'),b('C'),b('/'));
        vresult.getChildren().add(hresult);

        hresult = new HBox(1);
        hresult.getChildren().addAll(b('('),b(')'),b('$'),b('='));
        vresult.getChildren().add(hresult);

        Scene scene = new Scene(vresult);
        scene.setOnKeyTyped(e  -> handlekey(e));
        stage.setScene(scene);
    }


    public Button b(char c){
        Button button = new Button();
        button.setMaxSize(40,30);
        button.setMinSize(40,30);
        button.setText(c+"");
        button.setOnAction(value -> update(c));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void update(char c){
//        if (("-+/*=".contains(c+""))&&process.length()>=1){
//            {
//                System.out.println("sss");
//            }
//            char cur = this.process.charAt(process.length()-1);
//            if ("-+/*=".contains(cur+"")) {
//                update('\b');
//                {
//                    System.out.println(process);
//                }
//                update(c);
//            }
//        }
        if (c!='C'&&c!='\b'){
            this.process = process+c;
            calcul.setText(process);
        }
        if (c=='=') {
            this.tok.readString(this.process);
            result.setText(tok.calc.getResult()+"");
            this.process = "";
        }
        else if(c =='C'){

            this.process = "";
            calcul.setText("");
            this.result.setText(0+".");
        }
        else if(c=='\b'){
            process = process.substring(0,process.length()-1);
            this.calcul.setText(process);
        }
    }

    public void handlekey(KeyEvent e){
        String name  = "1234567890-+/*=C$";
        if (name.contains(e.getCharacter())||e.getCharacter().charAt(0)=='\b') {
            //Button button = b(e.getCharacter().charAt(0));
            update(e.getCharacter().charAt(0));
        }
    }
}