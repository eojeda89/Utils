package com.example.demo.targets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tus")
@Getter
@Setter
@NoArgsConstructor
public class Tu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	private int jobid;
	
	@NonNull
	private int idinjob;
	
	private String extid;
	
	private String srctext;
	
	private String pretrans;
	
	@NonNull
	private int origin;
	
	private String trans;
	
	@NonNull
	private int transcode;
	
	private String alttrans;
	
	@NonNull
	private int altcode;
	
	private String gtrans;
	
	private Integer bundleid;
	
	@NonNull
	private int status;
	
	@NonNull
	private int transstatus;
	
	@NonNull
	private int alttransstatus;
	
	@NonNull
	private int qastatus;
	
	@NonNull
	private int altqastatus;
	
	@NonNull
	private int altqestatus;
	
	private String qainfo;
	
	private String qeinfo;
	
	@NonNull
	private int qescore;
	
	@NonNull
	private int qascore;
	
	@NonNull
	private int revstatus;
	
	@NonNull
	private int qestatus;
		
	@NonNull
	private int revscore;
	
	@NonNull
	private double gdiss;
	
	@NonNull
	private double gdissalt;
	
	@NonNull
	private int actorid;
	
	@NonNull
	private int reviewerid;
	
	@NonNull
	private int mark;

	@NonNull
	private int bfuzzyval;

	@NonNull
	private int bfuzzyid;

	@NonNull
	private int hasrepetitions;

	private int translatorworker;
	private int revisorworker;
	private int evaluatorworker;
	private int annotatorworker;
	private int transcriberworker;
	private int vocalizerworker;
	private int taggerworker;
	private Timestamp updatets;

	public Tu(int id, int jobid, int idinjob, String extid, String srctext, String pretrans, int origin, String trans,
              int transcode, String alttrans, int altcode, String gtrans, Integer bundleid, int status, int transstatus,
              int alttransstatus, int qastatus, int altqastatus, int altqestatus, String qainfo, String qeinfo,
              int qescore, int qascore, int revstatus, int qestatus, int revscore, double gdiss, double gdissalt,
              int actorid, int reviewerid, int mark) {
		super();
		this.id = id;
		this.jobid = jobid;
		this.idinjob = idinjob;
		this.extid = extid;
		this.srctext = srctext;
		this.pretrans = pretrans;
		this.origin = origin;
		this.trans = trans;
		this.transcode = transcode;
		this.alttrans = alttrans;
		this.altcode = altcode;
		this.gtrans = gtrans;
		this.bundleid = bundleid;
		this.status = status;
		this.transstatus = transstatus;
		this.alttransstatus = alttransstatus;
		this.qastatus = qastatus;
		this.altqastatus = altqastatus;
		this.altqestatus = altqestatus;
		this.qainfo = qainfo;
		this.qeinfo = qeinfo;
		this.qescore = qescore;
		this.qascore = qascore;
		this.revstatus = revstatus;
		this.qestatus = qestatus;
		this.revscore = revscore;
		this.gdiss = gdiss;
		this.gdissalt = gdissalt;
		this.actorid = actorid;
		this.reviewerid = reviewerid;
		this.mark = mark;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobId, int idnjob, String extId, String srctext, String pretrans, int origin, String trans,
              int transcode, String alttrans, int altcode, String gtrans, int bundleId, int status, int transstatus,
              int alttransstatus, int qastatus, int altqstatus, String qainfo, String qeinfo, int qescore, int qascore,
              int revscore, double gdiss, double gdissalt, int actorId, int evaluatorId, int mark) {
		super();
		this.jobid = jobId;
		this.idinjob = idnjob;
		this.extid = extId;
		this.srctext = srctext;
		this.pretrans = pretrans;
		this.origin = origin;
		this.trans = trans;
		this.transcode = transcode;
		this.alttrans = alttrans;
		this.altcode = altcode;
		this.gtrans = gtrans;
		this.bundleid = bundleId;
		this.status = status;
		this.transstatus = transstatus;
		this.alttransstatus = alttransstatus;
		this.qastatus = qastatus;
		this.altqastatus = altqstatus;
		this.qainfo = qainfo;
		this.qeinfo = qeinfo;
		this.qescore = qescore;
		this.qascore = qascore;
		this.revscore = revscore;
		this.gdiss = gdiss;
		this.gdissalt = gdissalt;
		this.actorid = actorId;
		this.reviewerid = evaluatorId;
		this.mark = mark;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int id, int jobid, int idinjob, String extid, String srctext, String pretrans, int origin, String trans,
              int transcode, String alttrans, int altcode, String gtrans, Integer bundleid, int status, int transstatus,
              int alttransstatus, int qastatus, int altqastatus, String qainfo, String qeinfo, int qescore, int qascore,
              int revscore, double gdiss, double gdissalt, int actorid, int reviewerid, int mark) {
		super();
		this.id = id;
		this.jobid = jobid;
		this.idinjob = idinjob;
		this.extid = extid;
		this.srctext = srctext;
		this.pretrans = pretrans;
		this.origin = origin;
		this.trans = trans;
		this.transcode = transcode;
		this.alttrans = alttrans;
		this.altcode = altcode;
		this.gtrans = gtrans;
		this.bundleid = bundleid;
		this.status = status;
		this.transstatus = transstatus;
		this.alttransstatus = alttransstatus;
		this.qastatus = qastatus;
		this.altqastatus = altqastatus;
		this.qainfo = qainfo;
		this.qeinfo = qeinfo;
		this.qescore = qescore;
		this.qascore = qascore;
		this.revscore = revscore;
		this.gdiss = gdiss;
		this.gdissalt = gdissalt;
		this.actorid = actorid;
		this.reviewerid = reviewerid;
		this.mark = mark;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}
	
	public Tu(int id, int jobid, int idinjob, String extid, String srctext, String pretrans, int origin, String trans,
              int transcode, String alttrans, int altcode, String gtrans, Integer bundleid, int status, int transstatus,
              int alttransstatus, int qastatus, int altqastatus, String qainfo, String qeinfo, int qescore, int qascore,
              int revstatus, int revscore, double gdiss, double gdissalt, int actorid, int reviewerid, int mark) {
		super();
		this.id = id;
		this.jobid = jobid;
		this.idinjob = idinjob;
		this.extid = extid;
		this.srctext = srctext;
		this.pretrans = pretrans;
		this.origin = origin;
		this.trans = trans;
		this.transcode = transcode;
		this.alttrans = alttrans;
		this.altcode = altcode;
		this.gtrans = gtrans;
		this.bundleid = bundleid;
		this.status = status;
		this.transstatus = transstatus;
		this.alttransstatus = alttransstatus;
		this.qastatus = qastatus;
		this.altqastatus = altqastatus;
		this.qainfo = qainfo;
		this.qeinfo = qeinfo;
		this.qescore = qescore;
		this.qascore = qascore;
		this.revstatus = revstatus;
		this.revscore = revscore;
		this.gdiss = gdiss;
		this.gdissalt = gdissalt;
		this.actorid = actorid;
		this.reviewerid = reviewerid;
		this.mark = mark;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.origin = 0;
		this.transcode = 0;
		this.altcode = 0;
		this.status = 0;
		this.transstatus = 0;
		this.alttransstatus = 0;
		this.qastatus = 0;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2, String trans, String alttrans, int transcode) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.origin = 0;
		this.transcode = transcode;
		this.altcode = 0;
		this.status = 0;
		this.transstatus = 0;
		this.alttransstatus = 0;
		this.qastatus = 0;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
		this.trans = trans;
		this.alttrans = alttrans;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2, String trans2, int tU_STATUS_QAED,
              int tU_TRANSSTATUS_DONE, int defaulttranscode) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.trans = trans2;
		this.origin = 0;
		this.transcode = defaulttranscode;
		this.altcode = 0;
		this.status = tU_STATUS_QAED;
		this.transstatus = tU_TRANSSTATUS_DONE;
		this.alttransstatus = 0;
		this.qastatus = 0;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2, String trans2, int tU_STATUS_QAED,
              int tU_TRANSSTATUS_DONE, int tu_QA_STATUS, int defaulttranscode) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.trans = trans2;
		this.origin = 0;
		this.transcode = defaulttranscode;
		this.altcode = 0;
		this.status = tU_STATUS_QAED;
		this.transstatus = tU_TRANSSTATUS_DONE;
		this.alttransstatus = 0;
		this.qastatus = tu_QA_STATUS;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2, String trans2, String altTrans2, int tU_STATUS_QAED,
              int tU_TRANSSTATUS_DONE, int defaulttranscode) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.trans = trans2;
		this.alttrans = altTrans2;
		this.origin = 0;
		this.transcode = defaulttranscode;
		this.altcode = 0;
		this.status = tU_STATUS_QAED;
		this.transstatus = tU_TRANSSTATUS_DONE;
		this.alttransstatus = 0;
		this.qastatus = 0;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int jobid2, int cLines, String extId2, String srcText2, String trans2, String altTrans2, int tU_STATUS_QAED,
              int tU_TRANSSTATUS_DONE, int tu_QA_STATUS, int defaulttranscode) {
		this.jobid = jobid2;
		this.idinjob = cLines;
		this.extid = extId2;
		this.srctext = srcText2;
		this.trans = trans2;
		this.alttrans = altTrans2;
		this.origin = 0;
		this.transcode = defaulttranscode;
		this.altcode = 0;
		this.status = tU_STATUS_QAED;
		this.transstatus = tU_TRANSSTATUS_DONE;
		this.alttransstatus = 0;
		this.qastatus = tu_QA_STATUS;
		this.altqastatus = 0;
		this.altqestatus = 0;
		this.qescore = 0;
		this.qascore = 0;
		this.revstatus = 0;
		this.qestatus = 0;
		this.revscore = 0;
		this.gdiss = 0;
		this.gdissalt = 0;
		this.actorid = 0;
		this.reviewerid = 0;
		this.mark = 0;
		this.bfuzzyid = 0;
		this.bfuzzyval = 0;
		this.hasrepetitions = 0;
	}

	public Tu(int id, int jobid, int idinjob, String extid, String srctext, String pretrans, int origin, String trans,
              int transcode, String alttrans, int altcode, String gtrans, Integer bundleid, int status, int transstatus,
              int alttransstatus, int qastatus, int altqastatus, int altqestatus, String qainfo, String qeinfo, int qescore,
              int qascore, int revstatus, int qestatus, int revscore, double gdiss, double gdissalt, int actorid,
              int reviewerid, int mark, int bfuzzyval, int bfuzzyid, int hasrepetitions) {
		this.id = id;
		this.jobid = jobid;
		this.idinjob = idinjob;
		this.extid = extid;
		this.srctext = srctext;
		this.pretrans = pretrans;
		this.origin = origin;
		this.trans = trans;
		this.transcode = transcode;
		this.alttrans = alttrans;
		this.altcode = altcode;
		this.gtrans = gtrans;
		this.bundleid = bundleid;
		this.status = status;
		this.transstatus = transstatus;
		this.alttransstatus = alttransstatus;
		this.qastatus = qastatus;
		this.altqastatus = altqastatus;
		this.altqestatus = altqestatus;
		this.qainfo = qainfo;
		this.qeinfo = qeinfo;
		this.qescore = qescore;
		this.qascore = qascore;
		this.revstatus = revstatus;
		this.qestatus = qestatus;
		this.revscore = revscore;
		this.gdiss = gdiss;
		this.gdissalt = gdissalt;
		this.actorid = actorid;
		this.reviewerid = reviewerid;
		this.mark = mark;
		this.bfuzzyval = bfuzzyval;
		this.bfuzzyid = bfuzzyid;
		this.hasrepetitions = hasrepetitions;
	}
}
