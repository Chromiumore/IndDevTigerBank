package me.chromiumore.tigerbank.service;

import me.chromiumore.tigerbank.domain.BaseEntity;
import me.chromiumore.tigerbank.domain.Category;
import me.chromiumore.tigerbank.domain.Operation;
import me.chromiumore.tigerbank.domain.OperationType;
import me.chromiumore.tigerbank.repository.CategoryRepository;
import me.chromiumore.tigerbank.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    private final OperationsRepository operationsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AnalyticsService(OperationsRepository operationsRepository, CategoryRepository categoryRepository) {
        this.operationsRepository = operationsRepository;
        this.categoryRepository = categoryRepository;
    }

    public double getIncomeExpenseDifference(LocalDate startDate, LocalDate endDate) {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Map.Entry<Integer, BaseEntity> entry : operationsRepository.getAll().entrySet()) {
            Operation operation = (Operation) entry.getValue();
            if (!operation.getDate().isBefore(startDate) && !operation.getDate().isAfter(endDate)) {
                if (operation.getType() == OperationType.INCOME) {
                    totalIncome += operation.getAmount();
                } else {
                    totalExpense += operation.getAmount();
                }
            }
        }

        return totalIncome - totalExpense;
    }

    public Map<String, Double> getIncomeByCategory(LocalDate startDate, LocalDate endDate) {
        Map<String, Double> incomeByCategory = new HashMap<>();

        for (Map.Entry<Integer, BaseEntity> entry : operationsRepository.getAll().entrySet()) {
            Operation operation = (Operation) entry.getValue();
            if (operation.getType() == OperationType.INCOME &&
                    !operation.getDate().isBefore(startDate) &&
                    !operation.getDate().isAfter(endDate)) {

                Category category = (Category) categoryRepository.get(operation.getCategoryId());
                if (category != null) {
                    String categoryName = category.getName();
                    incomeByCategory.merge(categoryName, operation.getAmount(), Double::sum);
                }
            }
        }

        return incomeByCategory;
    }

    public Map<String, Double> getExpenseByCategory(LocalDate startDate, LocalDate endDate) {
        Map<String, Double> expenseByCategory = new HashMap<>();

        for (Map.Entry<Integer, BaseEntity> entry : operationsRepository.getAll().entrySet()) {
            Operation operation = (Operation) entry.getValue();
            if (operation.getType() == OperationType.EXPENSE &&
                    !operation.getDate().isBefore(startDate) &&
                    !operation.getDate().isAfter(endDate)) {

                Category category = (Category) categoryRepository.get(operation.getCategoryId());
                if (category != null) {
                    String categoryName = category.getName();
                    expenseByCategory.merge(categoryName, operation.getAmount(), Double::sum);
                }
            }
        }

        return expenseByCategory;
    }

    public Map<String, Double> getIncomeExpenseByPeriod(LocalDate startDate, LocalDate endDate) {
        Map<String, Double> result = new HashMap<>();

        double income = 0;
        double expense = 0;

        for (Map.Entry<Integer, BaseEntity> entry : operationsRepository.getAll().entrySet()) {
            Operation operation = (Operation) entry.getValue();
            if (!operation.getDate().isBefore(startDate) && !operation.getDate().isAfter(endDate)) {
                if (operation.getType() == OperationType.INCOME) {
                    income += operation.getAmount();
                } else {
                    expense += operation.getAmount();
                }
            }
        }

        result.put("Доходы", income);
        result.put("Расходы", expense);
        result.put("Разница", income - expense);

        return result;
    }
}