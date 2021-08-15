package com.cosmin.emailblaster.ui.emailList.list;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.databinding.EmailListItemBinding;

public class EmailItemViewHolder extends RecyclerView.ViewHolder {
    EmailListItemBinding binding;
    Email email;
    public EmailItemViewHolder(@NonNull View itemView, EmailListItemBinding binding) {
        super(itemView);
        this.binding = binding;
    }

    void setEmail(Email email) {
        this.email = email;
        binding.setEmail(email);
        binding.executePendingBindings();
    }
}
