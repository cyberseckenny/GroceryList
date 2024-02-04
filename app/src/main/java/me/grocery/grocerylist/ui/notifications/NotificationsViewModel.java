package me.grocery.grocerylist.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import me.grocery.grocerylist.ui.home.HomeFragment;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(HomeFragment.advice);
    }

    public LiveData<String> getText() {
        return mText;
    }
}