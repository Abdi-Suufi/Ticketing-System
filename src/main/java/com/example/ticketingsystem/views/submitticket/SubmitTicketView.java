package com.example.ticketingsystem.views.submitticket;

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

        // Populate ComboBox2 with choices
        PriorityComboBox.setItems("Low", "Medium", "High");

        //makes no sense to me. it's in a row instead of column
        column2.add(EffectComboBox, PriorityComboBox);

        Button Submit = new Button("Submit");
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
    }
}