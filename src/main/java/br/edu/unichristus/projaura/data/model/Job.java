package br.edu.unichristus.projaura.data.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_table")
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String title;
    
    @Column(nullable = false, length = 500)
    private String description;
    private String category;
    
    @Column(nullable = false)
    private boolean remote;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id")
    @JsonIgnore
    private Ngo ngo;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, category, remote);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        return Objects.equals(id, other.id) && Objects.equals(title, other.title) && Objects.equals(description, other.description)
        		&& Objects.equals(category, other.category) && Objects.equals(remote, other.remote);
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category + ", remote=" + remote + "]";
    }
}
