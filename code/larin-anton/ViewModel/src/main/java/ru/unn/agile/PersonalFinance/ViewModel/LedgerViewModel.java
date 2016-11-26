package ru.unn.agile.PersonalFinance.ViewModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.PersonalFinance.Model.Account;
import ru.unn.agile.PersonalFinance.Model.Ledger;

import java.util.List;
import java.util.stream.Collectors;

public class LedgerViewModel {
    private final Ledger ledgerModel = new Ledger();

    private final ListProperty<AccountViewModel> accountsProperty;
    private final ObjectProperty<AccountViewModel> selectedAccountProperty;

    public LedgerViewModel() {
        this.accountsProperty = new SimpleListProperty<>(getAccountViewModels());
        this.selectedAccountProperty = new SimpleObjectProperty<>();
    }

    public ListProperty<AccountViewModel> accountsProperty() {
        return this.accountsProperty;
    }

    public ObservableList<AccountViewModel> getAccounts() {
        return this.accountsProperty.get();
    }

    public ObjectProperty<AccountViewModel> selectedAccountProperty() {
        return this.selectedAccountProperty;
    }

    public AccountViewModel getSelectedAccount() {
        return this.selectedAccountProperty.get();
    }

    public void setSelectedAccount(AccountViewModel selectedAccountProperty) {
        this.selectedAccountProperty.set(selectedAccountProperty);
    }

    public void addAccount(final AccountViewModel accountVM) {
        ledgerModel.addAccount(accountVM.getAccount());
        accountsProperty.add(accountVM);
    }

    private ObservableList<AccountViewModel> getAccountViewModels() {
        List<Account> accounts = this.ledgerModel.getAccounts();
        List<AccountViewModel> accountModels = accounts.stream()
                .map(account -> new AccountViewModel(account))
                .collect(Collectors.toList());
        return FXCollections.observableList(accountModels);
    }
}
