package org.example.apispringfron;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.apispringfron.requestHandler.getHandler;
import org.example.apispringfron.requestHandler.postHandler;

public class HelloController {

    @FXML
    private Button get_employees_btn, get_employee_by_id_btn, get_employee_submitBtn, add_employee_btn,
            add_employee_submitBtn;

    @FXML
    private Label id_field_lbl;

    @FXML
    private TextArea result_tfd;

    @FXML
    private TextField get_employee_id_tfd, first_name_tfd, last_name_tfd, email_tfd;

    private getHandler getAllEmployeeOperation;
    private postHandler addEmployeeOperation;

    public void initialize() {

        addEmployeeOperation = new postHandler();
        getAllEmployeeOperation = new getHandler();
        result_tfd.setVisible(true);
        get_employee_submitBtn.setVisible(false);
        id_field_lbl.setVisible(false);
        get_employee_id_tfd.setVisible(false);
        first_name_tfd.setVisible(false);
        last_name_tfd.setVisible(false);
        email_tfd.setVisible(false);
        add_employee_submitBtn.setVisible(false);

        get_employees_btn.setOnAction(e -> {
            result_tfd.setVisible(true);
            get_employee_submitBtn.setVisible(false);
            id_field_lbl.setVisible(false);
            get_employee_id_tfd.setVisible(false);
            first_name_tfd.setVisible(false);
            last_name_tfd.setVisible(false);
            email_tfd.setVisible(false);
            add_employee_submitBtn.setVisible(false);

            String response = getAllEmployeeOperation.getAllEmployees();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(gson.fromJson(response, Object.class));

            result_tfd.setText(prettyJson);
        });

        get_employee_by_id_btn.setOnAction(e -> {
            result_tfd.setVisible(false);

            first_name_tfd.setVisible(false);
            last_name_tfd.setVisible(false);
            email_tfd.setVisible(false);
            add_employee_submitBtn.setVisible(false);
            get_employee_submitBtn.setVisible(true);
            id_field_lbl.setVisible(true);
            get_employee_id_tfd.setVisible(true);

            get_employee_submitBtn.setOnAction(event -> {
                String id = get_employee_id_tfd.getText().toString();

                String response = getAllEmployeeOperation.getEmployeeById(Integer.parseInt(id));
                if (response == null) {
                    get_employee_submitBtn.setVisible(false);
                    id_field_lbl.setVisible(false);
                    get_employee_id_tfd.setVisible(false);
                    result_tfd.setVisible(true);
                    result_tfd.setText("invalid ID");
                } else {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String prettyJson = gson.toJson(gson.fromJson(response, Object.class));


                    get_employee_submitBtn.setVisible(false);
                    id_field_lbl.setVisible(false);
                    get_employee_id_tfd.setVisible(false);
                    result_tfd.setVisible(true);

                    result_tfd.setText(prettyJson);
                }


            });
        });

        add_employee_btn.setOnAction(e -> {
            result_tfd.setVisible(false);

            get_employee_submitBtn.setVisible(false);
            id_field_lbl.setVisible(false);
            get_employee_id_tfd.setVisible(false);
            first_name_tfd.setVisible(true);
            last_name_tfd.setVisible(true);
            email_tfd.setVisible(true);
            add_employee_submitBtn.setVisible(true);

            add_employee_submitBtn.setOnAction(event -> {
                String firstName = first_name_tfd.getText().toString();
                String lastName = last_name_tfd.getText().toString();
                String email = email_tfd.getText().toString();

                String response = addEmployeeOperation.addEmployee(firstName, lastName, email);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJson = gson.toJson(gson.fromJson(response, Object.class));

                first_name_tfd.setVisible(false);
                last_name_tfd.setVisible(false);
                email_tfd.setVisible(false);
                add_employee_submitBtn.setVisible(false);

                result_tfd.setVisible(true);

                result_tfd.setText(prettyJson);

            });

        });
    }
}