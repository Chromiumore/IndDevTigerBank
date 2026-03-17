package me.chromiumore.tigerbank.service.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class DataService {
    protected String accountsExportName = "accounts_export";
    protected String categoriesExportName = "categories_export";
    protected String operationsExportName = "operations_export";
}
