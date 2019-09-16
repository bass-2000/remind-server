package com.bass2000.server.service;

import com.bass2000.server.entity.Remind;

import java.util.List;

public interface ReminderService {

    List<Remind> getAll();

    Remind getById(long id);

    Remind save(Remind remind);

    void remove(long id);
}
