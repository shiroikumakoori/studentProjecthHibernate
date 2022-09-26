package com.GamMedia.userService.entity;

import java.util.Date;

import javax.persistence.*;


import org.springframework.data.annotation.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

	@CreatedBy
	protected U createdBy;
	
	@CreatedDate
	protected Date createdDate;
	@LastModifiedBy
	protected U lastModifiedBy;
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate;
	
	
}
