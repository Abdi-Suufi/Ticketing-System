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
        ComboBox<String> comboBox1 = new ComboBox<>("Effect");
        comboBox1.setPlaceholder("Choose");

        // Populate ComboBox2 with choices
        comboBox1.setItems("Low", "Medium", "High");

        ComboBox<String> comboBox2 = new ComboBox<>("Priority");
        comboBox2.setPlaceholder("Choose");

        // Populate ComboBox2 with choices
        comboBox2.setItems("Low", "Medium", "High");

        //makes no sense to me. its in a row instead of column
        column2.add(comboBox1, comboBox2);

        Button buttonPrimary = new Button("Submit");
        buttonPrimary.addClassName("submit-ticket-view-button-1");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        column1.setWidth("min-content");
        column2.setWidth("min-content");

        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(column1);
        getContent().add(column2);
        getContent().add(buttonPrimary);
    }
}