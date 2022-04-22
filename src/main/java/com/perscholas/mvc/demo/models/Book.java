package com.perscholas.mvc.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Title can not be empty!!")
	@Size(min = 5, max = 40, message = "Title should be from 5-40 characters!!")
	private String title;
	@NotNull(message = "Description can not be empty!!")
	@Size(min = 5, max = 200, message = "Description should be from 5-200 characters!!")
	private String description;
	@NotNull(message = "Description can not be empty!!")
	@Size(min = 2, max = 40, message = "Please make between 2 and 40")
	private String language;
	@NotNull(message = "Pages can not be empty!!")
	@Min(100)
	private Integer numberOfPages;
	// This will not allow the createdAt column to be updated after creation
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public Book() {

	}

	public Book(@NotNull @Size(min = 5, max = 200) String title, @NotNull @Size(min = 5, max = 200) String description,
			@NotNull @Size(min = 3, max = 40) String language, @NotNull @Min(100) Integer numberOfPages) {
		super();
		this.title = title;
		this.description = description;
		this.language = language;
		this.numberOfPages = numberOfPages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
