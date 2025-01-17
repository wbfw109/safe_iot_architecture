package com.practice.dontcallme.backend.example;

import com.practice.dontcallme.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route(value = "testView", layout = MainLayout.class)
public class TestView extends VerticalLayout {

    private final CustomerRepository repo;

    private final CustomerEditor editor;

    final Grid<Customer> grid;

    final TextField filter;

    private final Button addNewBtn;

    public TestView(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Customer.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer1231233", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]

}


//
//@Route
//public class MainView extends VerticalLayout {
//
//    public MainView() {
//        VerticalLayout todosList = new VerticalLayout(); // (1)
//        TextField taskField = new TextField(); // (2)
//        Button addButton = new Button("Add"); // (3)
//        addButton.addClickShortcut(Key.ENTER);
//        addButton.addClickListener(click -> {
//            // (4)
//            Checkbox checkbox = new Checkbox(taskField.getValue());
//            todosList.add(checkbox);
//        });
//        add( // (5)
//                new H1("Vaadin Todo"),
//                todosList,
//                new HorizontalLayout(
//                        taskField,
//                        addButton
//                )
//        );
//    }
//}