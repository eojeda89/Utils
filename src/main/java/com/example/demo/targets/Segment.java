package com.example.demo.targets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "segments")
@Getter
@Setter
@NoArgsConstructor
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	private int tuid;
	
	@NonNull
	private int segtype;
	
	private String data;
	
	@NonNull
	private int origin;
	
	@NonNull
	private Timestamp ts;
	
	@NonNull
	private int actorid;
	
	@NonNull
	private int status;

	public Segment(int tuid, int segtype, String data, int origin, Timestamp ts, int actorid, int status) {
		super();
		this.tuid = tuid;
		this.segtype = segtype;
		this.data = data;
		this.origin = origin;
		this.ts = ts;
		this.actorid = actorid;
		this.status = status;
	}
	public Segment(int tuid, String data){
		this.tuid = tuid;
		this.segtype = 0;
		this.data = data;
		this.origin = 0;
		this.ts = new Timestamp(System.currentTimeMillis());
		this.actorid = 0;
		this.status = 0;
	}

	public Segment(int tuid, String data, int segtype){
		this.tuid = tuid;
		this.segtype = segtype;
		this.data = data;
		this.origin = 0;
		this.ts = new Timestamp(System.currentTimeMillis());
		this.actorid = 0;
		this.status = 0;
	}

	public Segment(int tuid, String data, int segtype, int actorid) {
		this.tuid = tuid;
		this.segtype = segtype;
		this.data = data;
		this.origin = 0;
		this.ts = new Timestamp(System.currentTimeMillis());
		this.actorid = actorid;
		this.status = 0;
	}
}
