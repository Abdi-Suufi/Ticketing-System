package com.example.ticketingsystem.views.submitticket;

import java.lang.String;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.example.ticketingsystem.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.notification.Notification;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Submit Ticket")
@Route(value = "Submit-Ticket", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
@Uses(Icon.class)
public class SubmitTicketView extends Composite<VerticalLayout> {

    public SubmitTicketView() {
        TextField textField = new TextField("Issue Topic");
        TextField textField2 = new TextField("Description");

        // Create HorizontalLayouts for columns
        HorizontalLayout column1 = new HorizontalLayout(textField, textField2);
        HorizontalLayout column2 = new HorizontalLayout();

        // Adding ComboBoxes to the second column
        ComboBox<String> EffectComboBox = new ComboBox<>("Effect");
        EffectComboBox.setPlaceholder("Choose");

        // Populate ComboBox2 with choices
        EffectComboBox.setItems("Low", "Medium", "High");

        ComboBox<String> PriorityComboBox = new ComboBox<>("Priority");
        PriorityComboBox.setPlaceholder("Choose");

        // Populate ComboBox2 with low medium and high
        PriorityComboBox.setItems("Low", "Medium", "High");

        //makes no sense to me. it's in a row instead of column
        column2.add(EffectComboBox, PriorityComboBox);

        Button Submit = new Button("Submit");
        // Submit.setEnabled(false);  // disabled by default until condition in the if statement below isn't met

        Submit.addClickListener(click -> {
            if(textField != null || textField2 != null || EffectComboBox != null || PriorityComboBox != null) {
                String textFieldValue = textField.getValue();//turning the characters into a number for later error handling
                String textField2Value = textField2.getValue();
                if (textFieldValue.length() < 2) {
                    throw new BpmnError("Incomplete_first_name_Field", "First Name must be at least 2 characters long");
                } else if (textField2Value.length() < 2) {
                    throw new BpmnError("Incomplete_last_name_Field", "Last Name must be at least 2 characters long");
                } else if(/*textField.isBlank() || */ textField.isEmpty()) { //isBlank is for the case of only whitespace being used and empty is if its just straight-up empty
                    throw new BpmnError("Incomplete_first_name_Field", "First Name can not be empty");
                } else if(textField2.isEmpty()) {
                    throw new BpmnError("Incomplete_last_name_Field", "Last Name can not be empty");
                } else if(EffectComboBox.isEmpty()) {
                    throw new BpmnError("Incomplete_effect_choice_Field", "Choose effect level");
                } else if(PriorityComboBox.getOptionalValue().isEmpty()) {
                    throw new BpmnError("Incomplete_priority_choice_Field", "Choose priority level");
                }
            } else {
                throw new BpmnError("Incomplete_ticket_submission_form_Field", "Ticket Submission must be complete");
            }

            Submit.addClassName("submit-ticket-view-button-1");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        column1.setWidth("min-content");
        column2.setWidth("min-content");

        Submit.setWidth("min-content");
        Submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(column1);
        getContent().add(column2);
        getContent().add(Submit);
    });
    }
}
