package me.chromiumore.services.output;

import me.chromiumore.entities.*;
import org.springframework.stereotype.Component;

@Component
public class CSVOutputStrategy extends OutputStrategy {

    @Override
    protected String dataToFormat() {
        StringBuilder sb = new StringBuilder();

        if (data.values().isEmpty()) {
            return "";
        }

        Object firstEntity = data.values().iterator().next();

        if (firstEntity instanceof BankAccount) {
            sb.append("ID,Название,Баланс\n");
            for (BankAccount account : (Iterable<BankAccount>) (Iterable) data.values()) {
                sb.append(account.getId()).append(",")
                        .append(account.getName()).append(",")
                        .append(account.getBalance()).append("\n");
            }
        } else if (firstEntity instanceof Category) {
            sb.append("ID,Тип,Название\n");
            for (Category category : (Iterable<Category>) (Iterable) data.values()) {
                sb.append(category.getId()).append(",")
                        .append(category.getType()).append(",")
                        .append(category.getName()).append("\n");
            }
        } else if (firstEntity instanceof Operation) {
            sb.append("ID,Тип,ID счета,Сумма;Дата,Описание,ID категории\n");
            for (Operation operation : (Iterable<Operation>) (Iterable) data.values()) {
                sb.append(operation.getId()).append(",")
                        .append(operation.getType()).append(",")
                        .append(operation.getBankAccountId()).append(",")
                        .append(operation.getAmount()).append(",")
                        .append(operation.getDate()).append(",")
                        .append(operation.getDescription()).append(",")
                        .append(operation.getCategoryId()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    protected String getFileExtension() {
        return ".csv";
    }
}