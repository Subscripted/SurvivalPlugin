package dev.subscripted.survivalplugin.modules.troll.trollmenu.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private Map<UUID, UUID> selectedUsers;

    public UserManager() {
        this.selectedUsers = new HashMap<>();
    }

    public void selectUser(UUID selectorUUID, UUID selectedUUID) {
        selectedUsers.put(selectedUUID, selectorUUID);
    }

    public void unselectUser(UUID selectedUUID) {
        selectedUsers.remove(selectedUUID);
    }

    public UUID getSelector(UUID selectedUUID) {
        return selectedUsers.get(selectedUUID);
    }

    public boolean isUserSelected(UUID selectedUUID) {
        return selectedUsers.containsKey(selectedUUID);
    }

    public Map<UUID, UUID> getSelectedUsers() {
        return new HashMap<>(selectedUsers);
    }
}
