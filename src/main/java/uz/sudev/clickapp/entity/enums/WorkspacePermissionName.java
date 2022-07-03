package uz.sudev.clickapp.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkspacePermissionName {
    CAN_ADD_OR_REMOVE_MEMBER("Add/Remove Members", "Gives user permission to add or remove to the workspace!", Arrays.asList(WorkspaceRoleName.ROLE_ADMIN, WorkspaceRoleName.ROLE_ADMIN)),
    CAN_MANAGE_STATUS("Edit Statuses", "Gives permission...", Arrays.asList(WorkspaceRoleName.ROLE_ADMIN, WorkspaceRoleName.ROLE_ADMIN));
    public final String name;
    public final String description;
    public final List<WorkspaceRoleName> workspaceRoleNames;

    WorkspacePermissionName(String name, String description, List<WorkspaceRoleName> workspaceRoleNames) {
        this.name = name;
        this.description = description;
        this.workspaceRoleNames = workspaceRoleNames;
    }
}
