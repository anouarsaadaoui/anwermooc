

package entities;

import java.util.Objects;

public class Categories {
    private int  id;
    private String cate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCate() {
        return cate;
    }

    public Categories(int id, String cate) {
        this.id = id;
        this.cate = cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public Categories() {
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", cate=" + cate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.cate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categories other = (Categories) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cate, other.cate)) {
            return false;
        }
        return true;
    }

    
}
