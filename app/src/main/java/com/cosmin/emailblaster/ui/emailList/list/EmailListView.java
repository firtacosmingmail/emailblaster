package com.cosmin.emailblaster.ui.emailList.list;

import com.cosmin.emailblaster.data.model.Email;

import java.util.List;

public class EmailListView {
    public List<Email> emails;
    public Boolean loading = false;
    public Integer errorMessage = null;
}
