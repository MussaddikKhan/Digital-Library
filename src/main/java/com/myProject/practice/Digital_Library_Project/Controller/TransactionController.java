package com.myProject.practice.Digital_Library_Project.Controller;

import com.myProject.practice.Digital_Library_Project.Dto.*;
import com.myProject.practice.Digital_Library_Project.Entity.Transaction;
import com.myProject.practice.Digital_Library_Project.Entity.TransactionStatus;
import com.myProject.practice.Digital_Library_Project.Entity.TransactionType;
import com.myProject.practice.Digital_Library_Project.Services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public  String makeTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) throws Exception {
        return  transactionService.transact(createTransactionRequest);
    }

    @GetMapping("/get/txn/{id}")
    public  Transaction getTxnById(@PathVariable  String id){
        return transactionService.getTxnById(id);
    }

    @GetMapping("/get/status")
    public List<ReturnTransactionResponse> returnTransactionByStatus(@RequestParam TransactionStatus transactionStatus){
        return transactionService.returnTransactionByStatus(transactionStatus);
    }

    @GetMapping("/getByType/{transactionType}")
    public List<ReturnTransactionResponse> returnTransactionByType(@PathVariable TransactionType transactionType){
        return transactionService.returnTransactionByType(transactionType);
    }

    @GetMapping("/getBookByTxnType")
    public  List<ReturnBookResponse> getBookByTxnType(@RequestParam TransactionType transactionType){
        return  transactionService.getBookByTxnType(transactionType);
    }
    @GetMapping("/getStudentsWithOverdue")
    public  List<ReturnStudentWithOverDueResponse> getStudentsWithOverDue(){
        return   transactionService.getStudentsWithOverDue();
    }

}
