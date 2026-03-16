package me.chromiumore.tigerbank.service.output;

import me.chromiumore.tigerbank.domain.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CSVOutputStrategy extends OutputStrategy {

    public CSVOutputStrategy() {
        super(".csv");
    }

    @Override
    protected String dataToFormat() {
        StringBuilder sb = new StringBuilder();

        if (data.values().isEmpty()) {
            return "";
        }

        Object firstEntity = data.values().iterator().next();

        if (firstEntity instanceof BankAccount) {
            sb.append("ID,Название,Баланс\n");
            for (Map.Entry<Integer, BaseEntity> entry : data.entrySet()) {
                BankAccount account = (BankAccount) entry.getValue();
                int id = entry.getKey();
                sb.append(id).append(",")
                        .append(account.getName()).append(",")
                        .append(account.getBalance()).append("\n");
            }
        } else if (firstEntity instanceof Category) {
            sb.append("ID,Тип,Название\n");
            for (Map.Entry<Integer, BaseEntity> entry : data.entrySet()) {
                Category category = (Category) entry.getValue();
                int id = entry.getKey();
                sb.append(id).append(",")
                        .append(category.getType()).append(",")
                        .append(category.getName()).append("\n");
            }
        } else if (firstEntity instanceof Operation) {
            sb.append("ID,Тип,ID счета,Сумма;Дата,Описание,ID категории\n");
            for (Map.Entry<Integer, BaseEntity> entry : data.entrySet()) {
                Operation operation = (Operation) entry.getValue();
                int id = entry.getKey();
                sb.append(id).append(",")
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
}