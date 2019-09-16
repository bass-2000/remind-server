package com.bass2000.server.controller;

import com.bass2000.server.entity.Remind;
import com.bass2000.server.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Remind> getAllReminders() {
        return reminderService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Remind getReminder(@PathVariable("id") long remindID) {
        return reminderService.getById(remindID);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Remind saveReminder(@RequestBody Remind remind) {
        return reminderService.save(remind);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteReminderById(@PathVariable("id") long remindID) {
        reminderService.remove(remindID);
    }
}
