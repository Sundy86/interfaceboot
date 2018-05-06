package com.sc.springmvc.model;

import java.io.Serializable;

public class CaseSystem implements Serializable {
    private String systemid;

    private String systemname;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getSystemid() {
        return systemid;
    }

    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname == null ? null : systemname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CaseSystem other = (CaseSystem) that;
        return (this.getSystemid() == null ? other.getSystemid() == null : this.getSystemid().equals(other.getSystemid()))
            && (this.getSystemname() == null ? other.getSystemname() == null : this.getSystemname().equals(other.getSystemname()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSystemid() == null) ? 0 : getSystemid().hashCode());
        result = prime * result + ((getSystemname() == null) ? 0 : getSystemname().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}