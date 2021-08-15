package com.cosmin.emailblaster.ui.emailList.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.databinding.EmailListItemBinding;
import com.cosmin.emailblaster.utils.BindableRecyclerViewAdapter;

import java.util.List;

import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

public class EmailListAdapter extends BindableRecyclerViewAdapter<EmailItemViewHolder> {

    private List<Email> emails;

    @NonNull
    @Override
    public EmailItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EmailListItemBinding itemBinding = DataBindingUtil.inflate(inflater, R.layout.email_list_item, parent, false);

        return new EmailItemViewHolder(itemBinding.getRoot(), itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailItemViewHolder holder, int position) {
        holder.setEmail(emails.get(position));
    }

    @Override
    public int getItemCount() {
        if ( emails == null ) {
            return 0;
        }else {
            return emails.size();
        }
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
