package com.vnit.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vnit.api.common.RestUtil;
import com.vnit.api.entity.BillHeader;
import com.vnit.api.entity.BillTypeMst;
import com.vnit.api.entity.CourseMst;
import com.vnit.api.entity.CustomerMst;
import com.vnit.api.entity.Department;
import com.vnit.api.entity.DeptdtlMst;
import com.vnit.api.entity.DepttypeMst;
import com.vnit.api.entity.EventmasterMst;
import com.vnit.api.entity.ExamMst;
import com.vnit.api.entity.ItemMst;
import com.vnit.api.entity.PeopleMst;
import com.vnit.api.entity.Scholar;
import com.vnit.api.entity.ScreenHeader;
import com.vnit.api.entity.ScreenMst;
import com.vnit.api.entity.ScreeneventMst;
import com.vnit.api.entity.ScreenfieldmasterMst;
import com.vnit.api.entity.ScreengroupMst;
import com.vnit.api.entity.ScreenjoinconditionMst;
import com.vnit.api.entity.ScreenlistHeader;
import com.vnit.api.entity.ScreenmappingconditionMst;
import com.vnit.api.entity.ScreenmappingqueryMst;
import com.vnit.api.entity.StudentMst;
import com.vnit.api.file.model.SimpleDataModel;
import com.vnit.api.file.model.SimpleDataModelContoller;
import com.vnit.api.file.model.SimpleDataModelHtml;
import com.vnit.api.file.model.SimpleDataModelRepo;
import com.vnit.api.file.model.SimpleDataModelTS;
import com.vnit.api.repo.ScreenRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import main.java.com.vnit.api.file.model.SimpleDataModelApi;
import main.java.com.vnit.api.file.utility.MapsUtil;
import org.springframework.stereotype.Component;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Component
public class CustomController {
	
	@Autowired
	EntityManager em;
     //   public static  ServletContext svrcontext;
        
     //   public static String applicationpath=svrcontext.getContextPath();
        
         // public static  ApplicationContext context;

	
	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_customer_list", produces = "application/json")
	@ApiOperation(value = "Get customer list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getCustomerList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<CustomerMst> customerList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from customer order by ccode limit 10";
				customerList = em.createNativeQuery(query,CustomerMst.class).getResultList();
			} else {
				query ="select * from customer where cname like '%" + name + "%' order by ccode desc limit 10";
				customerList = em.createNativeQuery(query,CustomerMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(customerList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_people_list", produces = "application/json")
	@ApiOperation(value = "Get people list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getPeopleList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<PeopleMst> peopleList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from people order by peopleid limit 10";
				peopleList = em.createNativeQuery(query,PeopleMst.class).getResultList();
			} else {
				query ="select * from people where name like '%" + name + "%' order by peopleid desc limit 10";
				peopleList = em.createNativeQuery(query,PeopleMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(peopleList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_student_list", produces = "application/json")
	@ApiOperation(value = "Get student list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getStudentList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<StudentMst> studentList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from student order by studentid limit 10";
				studentList = em.createNativeQuery(query, StudentMst.class).getResultList();
			} else {
				query ="select * from student where name like '%" + name + "%' order by studentid desc limit 10";
				studentList = em.createNativeQuery(query, StudentMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(studentList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
             	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_exam_list", produces = "application/json")
	@ApiOperation(value = "Get exam list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getExamList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<ExamMst> examList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from exam order by examid limit 10";
				examList = em.createNativeQuery(query, ExamMst.class).getResultList();
			} else {
				query ="select * from exam where subject like '%" + name + "%' order by examid desc limit 10";
				examList = em.createNativeQuery(query, ExamMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(examList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
@ResponseStatus(code = HttpStatus.OK)
@GetMapping(path = "/get_course_list", produces = "application/json")
@ApiOperation(value = "Get course list", httpMethod = "GET")
@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
public String getCourseList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		List<CourseMst> courseList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from course order by courseid limit 10";
				courseList = em.createNativeQuery(query, CourseMst.class).getResultList();
			} else {
				query ="select * from course where name like '%" + name + "%' order by courseid desc limit 10";
				courseList = em.createNativeQuery(query, CourseMst.class).getResultList();
			}

			response.add("data", JsonParser.parseString(mapper.writeValueAsString(courseList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}

		response.addProperty("code", 200);
		response.addProperty("status", "Success");

		return response.toString();
}

        
                  @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_eventmaster_list", produces = "application/json")
	@ApiOperation(value = "Get eventmaster list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEventMasterList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<EventmasterMst> eventmasterlist = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from eventmaster order by eventid limit 10";
				eventmasterlist = em.createNativeQuery(query,EventmasterMst.class).getResultList();
			} else {
				query ="select * from eventmaster where eventname like '%" + name + "%' order by eventid desc limit 10";
				eventmasterlist = em.createNativeQuery(query,EventmasterMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(eventmasterlist)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_eventmaster_name_id_list", produces = "application/json")
	@ApiOperation(value = "Get eventmaster id to name list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEventMasteNametoID(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<String> eventmasterlist = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select eventid,eventname from eventmaster order by eventid limit 10";
				eventmasterlist = em.createNativeQuery(query).getResultList();
			} else {
				query ="select eventid,eventname from eventmaster where eventname like '%" + name + "%' order by eventid desc limit 10";
				eventmasterlist = em.createNativeQuery(query).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(eventmasterlist)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenevent_list", produces = "application/json")
	@ApiOperation(value = "Get screenevent list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenEvent(@RequestParam String screenevent) {
		JsonObject response = new JsonObject();
		
		List<ScreeneventMst> screeneventlist = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenevent)) {
				query ="select * from screenevent order by screeneventid limit 10";
				screeneventlist = em.createNativeQuery(query,ScreeneventMst.class).getResultList();
			} else {
				query ="select * from screenevent where screeneventid like '%" + screenevent + "%' order by screeneventid desc limit 10";
				screeneventlist = em.createNativeQuery(query,ScreeneventMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screeneventlist)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
         @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenfieldmaster_list", produces = "application/json")
	@ApiOperation(value = "Get screenfieldmaster list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenFieldmaster(@RequestParam Integer screenfieldid) {
		JsonObject response = new JsonObject();
		
		List<ScreenfieldmasterMst> screenfieldmaster = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenfieldid)) {
				query ="select * from screenfieldmaster order by screenfieldid";
				screenfieldmaster = em.createNativeQuery(query,ScreenfieldmasterMst.class).getResultList();
			} else {
				query ="select * from screenfieldmaster where screenfieldid like '%" + screenfieldid + "%' order by screenfieldid";
				screenfieldmaster = em.createNativeQuery(query,ScreenfieldmasterMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenfieldmaster)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenjoincondition_list", produces = "application/json")
	@ApiOperation(value = "Get screenjoincondition list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenjoinCondtion(@RequestParam Integer screenjoinID) {
		JsonObject response = new JsonObject();
		
		List<ScreenjoinconditionMst> customerList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenjoinID)) {
				query ="select * from screenjoincondition order by screenjoinid limit 10";
				customerList = em.createNativeQuery(query,ScreenjoinconditionMst.class).getResultList();
			} else {
				query ="select * from screenjoincondition where screenjoinid like '%" + screenjoinID + "%' order by screenjoinid desc limit 10";
				customerList = em.createNativeQuery(query,ScreenjoinconditionMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(customerList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenmappingcondition_list", produces = "application/json")
	@ApiOperation(value = "Get screenmappingcondition list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenmappingCondtion(@RequestParam Integer screenmappingID) {
		JsonObject response = new JsonObject();
		
		List<ScreenmappingconditionMst> customerList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenmappingID)) {
				query ="select * from screenmappingcondition order by screenmappingid limit 10";
				customerList = em.createNativeQuery(query,ScreenmappingconditionMst.class).getResultList();
			} else {
				query ="select * from screenmappingcondition where screenmappingid like '%" + screenmappingID + "%' order by screenmappingid desc limit 10";
				customerList = em.createNativeQuery(query,ScreenmappingconditionMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(customerList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenmappingquery_list", produces = "application/json")
	@ApiOperation(value = "Get screenmappingquery list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenmappingQuery(@RequestParam Integer screenmappingqueryID) {
		JsonObject response = new JsonObject();
		
		List<ScreenmappingqueryMst> customerList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenmappingqueryID)) {
				query ="select * from screenmappingquery order by screenmappingqueryid limit 10";
				customerList = em.createNativeQuery(query,ScreenmappingqueryMst.class).getResultList();
			} else {
				query ="select * from screenmappingquery where screenmappingqueryid like '%" + screenmappingqueryID + "%' order by screenmappingqueryid desc limit 10";
				customerList = em.createNativeQuery(query,ScreenmappingqueryMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(customerList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenlist_to_id", produces = "application/json")
	@ApiOperation(value = "Get screenlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenList_1(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select screenid,screenname from screen order by screenid limit 10";
				screenList = em.createNativeQuery(query).getResultList();
			} else {
				query ="select screenid,screenname from screen where screenname like '%" + name + "%' order by screenid desc limit 10";
				screenList = em.createNativeQuery(query).getResultList();
//                                System.out.println(query);
                        }
                                                     System.out.println("Screen List is of type : " + screenList.getClass());
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
       
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenid_from_screenname", produces = "application/json")
	@ApiOperation(value = "Get screenlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenType_1(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
                                                    query ="select screentype from screen where screenname = '" + name + "';";
                                                    screenList = em.createNativeQuery(query).getResultList();
                                                    System.out.println(screenList);
                                                    response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		return response.toString();
	}
       
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_tableNames_from_screenname", produces = "application/json")
	@ApiOperation(value = "Get screenlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getTableNames(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
                                                    query ="select basetable from screen, screengroup where screen.screenid = screengroup.screenid and screen.screenname = '" + name + "';";
                                                    screenList = em.createNativeQuery(query).getResultList();
                                                    System.out.println(screenList);
                                                    response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		return response.toString();
	}
       
        
         @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screengroupid_from_screenid", produces = "application/json")
	@ApiOperation(value = "Get screenlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenGroupList_1(@RequestParam Integer screenid) {
		JsonObject response = new JsonObject();
		
		List screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenid)) {
				query ="select screengroupid,gpurpose,mastergroupname from screengroup order by screenid limit 10";
				screenList = em.createNativeQuery(query).getResultList();
			} else {
				query ="select screengroupid,gpurpose,mastergroupname from screengroup where screenid like '%" + screenid + "%' order by screenid desc limit 10";
				screenList = em.createNativeQuery(query).getResultList();
//                                System.out.println(query);
                        }
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screenfieldlist", produces = "application/json")
	@ApiOperation(value = "Get screenfieldlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenField_1(@RequestParam Integer screenID, Integer screengroupID) {
		JsonObject response = new JsonObject();
		List screenfieldList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenID)) {
				query ="select screenfieldid,label from screenfieldmaster order by screenfieldid";
				screenfieldList = em.createNativeQuery(query).getResultList();
			} else {
				query ="select screenfieldid,label from screenfieldmaster where screenid like '%" + screenID + "%' and screengroupid = " + screengroupID + " order by screenfieldid";
				screenfieldList = em.createNativeQuery(query).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenfieldList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_item_list", produces = "application/json")
	@ApiOperation(value = "Get item list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getItemList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<ItemMst> itemList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from item order by itcode limit 10";
				itemList = em.createNativeQuery(query,ItemMst.class).getResultList();
			} else {
				query ="select * from item where itname like '%" + name + "%' order by itcode desc limit 10";
				itemList = em.createNativeQuery(query,ItemMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(itemList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_bill_list", produces = "application/json")
	@ApiOperation(value = "Get bill list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getBillList(@RequestParam String billno) {
		JsonObject response = new JsonObject();
		
		List<BillHeader> billList = new ArrayList<>();
		String query = "";
		try {
			if (RestUtil.isNull(billno)) {
				query ="select * from billhdr order by billno limit 10";
				billList = em.createNativeQuery(query,BillHeader.class).getResultList();
			}else {
				query ="select * from billhdr where billno like '%" + billno + "%' order by billno desc limit 10";
				billList = em.createNativeQuery(query,BillHeader.class).getResultList();
			}
			
			JsonArray billArray = new JsonArray();
			for (BillHeader bill : billList) {
				billArray.add(JsonParser.parseString(bill.toString()));
			}
			response.add("data", billArray);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_scholar_list", produces = "application/json")
	@ApiOperation(value = "Get scholar list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScholarList(@RequestParam String scholarid) {
		JsonObject response = new JsonObject();
		
		List<Scholar> scholarList = new ArrayList<>();
		String query = "";
		try {
			if (RestUtil.isNull(scholarid)) {
				query ="select * from scholar order by scholarid limit 10";
				scholarList = em.createNativeQuery(query,Scholar.class).getResultList();
			}else {
				query ="select * from scholar where scholarid like '%" + scholarid + "%' order by scholarid desc limit 10";
				scholarList = em.createNativeQuery(query,Scholar.class).getResultList();
			}
			
			JsonArray scholarArray = new JsonArray();
			for (Scholar scholar : scholarList) {
				scholarArray.add(JsonParser.parseString(scholar.toString()));
			}
			response.add("data", scholarArray);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
	
	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_bill_type_list", produces = "application/json")
	@ApiOperation(value = "Get bill type list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getBillTypeList(@RequestParam String type) {
		JsonObject response = new JsonObject();
		
		List<BillTypeMst> billTypeList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(type)) {
				query ="select * from billtype order by billid limit 10";
				billTypeList = em.createNativeQuery(query,BillTypeMst.class).getResultList();
			} else {
				query ="select * from billtype where billtp like '%" + type + "%' order by billid limit 10";
				billTypeList = em.createNativeQuery(query,BillTypeMst.class).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(billTypeList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        	
	@SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/item_list_by_bill_type", produces = "application/json")
	@ApiOperation(value = "Get item list by bill type", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getItemListByBillType(@RequestParam String type) {
		JsonObject response = new JsonObject();
		String status = "Failed";
		
		List<ItemMst> itemList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(type)) {
				response.add("data", new JsonArray());
				response.addProperty("error", "'type' is required");
			} else {
				query ="select t.itcode, i.itname, i.itrate\r\n" + 
						"from billtype t, item i\r\n" + 
						"	where i.itcode = t.itcode\r\n" + 
						"	and t.billtp = " + type;
				itemList = em.createNativeQuery(query,ItemMst.class).getResultList();
				response.add("data", JsonParser.parseString(mapper.writeValueAsString(itemList)));
				status = "Success";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", status);
		
		return response.toString();
	}
  
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_table_list_mysql", produces = "application/json")
	@ApiOperation(value = "Get table list(Enter Databse name as input)", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getTableList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		
		List<String> screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
                            System.out.println("List:1");
				//query ="select table_name from information_schema.tables WHERE table_schema = '"+name+"' order by table_name limit 10;";
				query ="select table_name from information_schema.tables WHERE table_schema =database() order by table_name limit 10;";
                                screenList = em.createNativeQuery(query).getResultList();
                                System.out.println(screenList);
			} else {
                            System.out.println("List:2");
				query ="SELECT table_name FROM information_schema.tables WHERE table_schema =database();";
				screenList = em.createNativeQuery(query).getResultList();
                                System.out.println(screenList);
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(screenList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_screen_list", produces = "application/json")
	@ApiOperation(value = "Get screen list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenList(@RequestParam String screenid) {
		JsonObject response = new JsonObject();
		ScreenRepo repo = new ScreenRepo();
		List<ScreenHeader> screenList = new ArrayList<>();
		String query = "";
		try {
			if (RestUtil.isNull(screenid)) {
				query ="select * from screen order by screenid limit 10";
				screenList = em.createNativeQuery(query,ScreenHeader.class).getResultList();
			}else {
				query ="select * from screen where screenid like '%" + screenid + "%' order by screenid desc limit 10";
				screenList = em.createNativeQuery(query,ScreenHeader.class).getResultList();
			}
			System.out.println(query);
			JsonArray screenArray = new JsonArray();
			for (ScreenHeader screen : screenList) {
                            if(screen !=null )
                            {    System.out.println(" screen "+screen);
//                                                                int id = screen.getScreenid();
//                                                                ScreenMst screenObj = repo.getScreen(id);
//                                                                ScreenController.map1.put(screen.getScreenname(), screenObj);
//                                                                System.out.println("screen object added to map");
				screenArray.add(JsonParser.parseString(screen.toString()));
                            }
			}
			response.add("data", screenArray);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
         @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_complete_screenlist", produces = "application/json")
	@ApiOperation(value = "Get screenlist", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getScreenList_2(@RequestParam String screenlistid) {
		JsonObject response = new JsonObject();
		
		List<ScreenlistHeader> screenList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screenlistid)) {
				query ="select * from screenlisthdr order by screenlistid limit 10";
				screenList = em.createNativeQuery(query,ScreenlistHeader.class).getResultList();
			} else {
				query ="select * from screenlisthdr where screenlistid like '%" + screenlistid + "%' order by screenlistid desc limit 10";
				screenList = em.createNativeQuery(query,ScreenlistHeader.class).getResultList();
                                
                        }
			
			JsonArray screenArray = new JsonArray();
			for (ScreenlistHeader screen : screenList) {
                                System.out.println(screen);
				screenArray.add(JsonParser.parseString(screen.toString()));
			}
                        response.add("data", screenArray);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}

              @SuppressWarnings("unchecked")
@ResponseStatus(code = HttpStatus.OK)
@GetMapping(path = "/get_department_list", produces = "application/json")
@ApiOperation(value = "Get Department list", httpMethod = "GET")
@ApiResponse(code = 200, message = "Returns a 200 response code if successfull")
public String getDepartmentList(@RequestParam String deptid) {
		JsonObject response = new JsonObject();
		List<Department> departmentList = new ArrayList<>();
		String query = "";
		try {
			if (RestUtil.isNull(deptid)) {
				query ="select * from department order by deptid limit 10";
				departmentList = em.createNativeQuery(query, Department.class).getResultList();
			}else {
				query ="select * from department where deptid like '%" + deptid + "%' order by deptid desc limit 10";
				departmentList = em.createNativeQuery(query, Department.class).getResultList();
			}

			JsonArray departmentArray = new JsonArray();
                                                     
			for (Department department : departmentList) {
                                                                departmentArray.add(JsonParser.parseString(department.toString()));
                                                                
			}
			response.add("data", departmentArray);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}

		response.addProperty("code", 200);
		response.addProperty("status", "Success");

		return response.toString();
}

@SuppressWarnings("unchecked")
@ResponseStatus(code = HttpStatus.OK)
@GetMapping(path = "/get_depttype_list", produces = "application/json")
@ApiOperation(value = "Get depttype list", httpMethod = "GET")
@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
public String getDepttypeList(@RequestParam String name) {
		JsonObject response = new JsonObject();
		List<DepttypeMst> depttypeList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="select * from depttype order by depttypeid limit 10";
				depttypeList = em.createNativeQuery(query, DepttypeMst.class).getResultList();
			} else {
				query ="select * from depttype where name like '%" + name + "%' order by depttypeid desc limit 10";
				depttypeList = em.createNativeQuery(query, DepttypeMst.class).getResultList();
			}

			response.add("data", JsonParser.parseString(mapper.writeValueAsString(depttypeList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}

		response.addProperty("code", 200);
		response.addProperty("status", "Success");

		return response.toString();
}



@SuppressWarnings("unchecked")
@ResponseStatus(code = HttpStatus.OK)
@GetMapping(path = "/courseid_by_depttp", produces = "application/json")
@ApiOperation(value = "Get courseid by depttp", httpMethod = "GET")
@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
public String getcourseidBydepttp(@RequestParam String type) {
		JsonObject response = new JsonObject();
		String status = "Failed";

		List<CourseMst> courseList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
                try {
			if (RestUtil.isNull(type)) {
				response.add("data", new JsonArray());
				response.addProperty("error", "'type' is required");
			} else {
				query = "select c.courseid, c.coursename, c.credits from course c, depttype d where c.courseid = d.courseid and d.depttp = " + type + ";"; 
				courseList = em.createNativeQuery(query,CourseMst.class).getResultList();
				response.add("data", JsonParser.parseString(mapper.writeValueAsString(courseList)));
				status = "Success";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}

		response.addProperty("code", 200);
		response.addProperty("status", status);
		return response.toString();
	}




                @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_component_list", produces = "application/json")
	@ApiOperation(value = "Get component list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getComponent(@RequestParam String component) {
		JsonObject response = new JsonObject();
		
		List<String> customerList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(component)) {
				query ="select screencomponentid,screencomponent from screencomponent order by screencomponentid limit 10";
				customerList = em.createNativeQuery(query).getResultList();
			} else {
				query ="select screencomponentid,screencomponent from screencomponent where screencomponent like '%" + component + "%' order by screencomponentid desc limit 10";
				customerList = em.createNativeQuery(query).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(customerList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_column_list", produces = "application/json")
	@ApiOperation(value = "Get column list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getMastercolumn(@RequestParam String name) {
		JsonObject response = new JsonObject();
		List<String> List = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(name)) {
				query ="";
				List = em.createNativeQuery(query).getResultList();
			} else {
				query ="SELECT column_name\n" +
"FROM information_schema.columns\n" +
"WHERE table_name = '"+name+"'";
				List = em.createNativeQuery(query).getResultList();
			}
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(List)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
           @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_component_name_list", produces = "application/json")
	@ApiOperation(value = "Get mastercolumn list", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getComponentName(@RequestParam Integer id) {
		JsonObject response = new JsonObject();
		List<String> List = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(id)) {
				query ="";
				List = em.createNativeQuery(query).getResultList();
			} else {
                            if(id==1){
                                query ="select screenname from screen";
                                
                            }else if(id==2){
                                query ="select gpurpose from screengroup";
                                
                            }else if (id==3){
                                query ="select field_name from screenfieldmaster";
                            }else{
                                query="select * from screencompoenet where"+id;
                            }
				List = em.createNativeQuery(query).getResultList();
			}
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(List)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_basetablecolumn", produces = "application/json")
	@ApiOperation(value = "Get base table column", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getBaseTableColumn(@RequestParam String screengroupid) {
		JsonObject response = new JsonObject();
		
		List<String> basetablecolumnList = new ArrayList<>();
		String query = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (RestUtil.isNull(screengroupid)) {
				query ="select basetable from screengroup order by screengroupid limit 10";
				basetablecolumnList = em.createNativeQuery(query).getResultList();
			} else {
				query ="select basetable from screengroup where screengroupid like '%" + screengroupid + "%' order by screengroupid desc limit 10";
				basetablecolumnList = em.createNativeQuery(query).getResultList();
			}
			
			response.add("data", JsonParser.parseString(mapper.writeValueAsString(basetablecolumnList)));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.add("data", new JsonArray());
		}
		
		response.addProperty("code", 200);
		response.addProperty("status", "Success");
		
		return response.toString();
	}
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntityFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                Map<String,String> map=new HashMap<>();
                SimpleDataModel s1=new SimpleDataModel(map);
                
                switch(getScreenType(screenname)) {
                    case 1 :                
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeEntity(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                }
                
                
                return response.toString();
        }
        
                @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity1_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntity1File(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                        Map<String,String> map=new HashMap<>();
                        SimpleDataModel s1=new SimpleDataModel(map);
                
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Entity(names, 1));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                          
                
                return response.toString();
        }
        
                    @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity2_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntity2File(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                        Map<String,String> map=new HashMap<>();
                        SimpleDataModel s1=new SimpleDataModel(map);
                
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Entity(names, 2));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                          
                
                return response.toString();
        }
        
                    @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity3_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntity3File(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                        Map<String,String> map=new HashMap<>();
                        SimpleDataModel s1=new SimpleDataModel(map);
                
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Entity(names, 3));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                          
                
                return response.toString();
        }
        
                    @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity4_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntity4File(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                        Map<String,String> map=new HashMap<>();
                        SimpleDataModel s1=new SimpleDataModel(map);
                
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Entity(names, 4));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                          
                
                return response.toString();
        }
        
                    @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_entity5_file", produces = "application/json")
	@ApiOperation(value = "Get entity file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getEntity5File(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                        Map<String,String> map=new HashMap<>();
                        SimpleDataModel s1=new SimpleDataModel(map);
                
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Entity(names, 5));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                          
                
                return response.toString();
        }
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_controller_file", produces = "application/json")
	@ApiOperation(value = "Get controller file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getControllerFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                Map<String,String> map=new HashMap<>();
                SimpleDataModelContoller s1=new SimpleDataModelContoller(map);
                
                switch(getScreenType(screenname)) {
                    case 1 : 
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeController(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 2 :
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Controller(names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 3 :
                            ArrayList<String> s3names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Controller(s3names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                          
                }
                
               	return response.toString();
        }
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_repo_file", produces = "application/json")
	@ApiOperation(value = "Get repo file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getRepoFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                Map<String,String> map=new HashMap<>();
                SimpleDataModelRepo s1=new SimpleDataModelRepo(map);
               
                switch(getScreenType(screenname)) {
                    case 1 : 
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeRepo(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 2 :
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Repo(names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                             break;
                             
                    case 3 : 
                            ArrayList<String> s3names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2Repo(s3names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                    
                }
                
            	return response.toString();
        }
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_html_file", produces = "application/json")
	@ApiOperation(value = "Get html file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getHTMLFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                Map<String,String> map=new HashMap<>();
                SimpleDataModelHtml s1=new SimpleDataModelHtml(map);
                
                 switch(getScreenType(screenname)) {
                    case 1 : 
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeHtmlFile(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 2 :
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2HtmlFile(names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            
                            break;

                    case 3 : 
                            ArrayList<String> s3names = getTwoTableName(screenname);
                            ScreenmappingconditionMst  mappingObject = getScreenMappingCondition(screenname);
                            response.addProperty("data", s1.makeS3HtmlFile(s3names, mappingObject));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                    
                }
                
             	return response.toString();
	}
       
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_ts_file", produces = "application/json")
	@ApiOperation(value = "Get ts file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getTSFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
                Map<String,String> map=new HashMap<>();
                SimpleDataModelTS s1=new SimpleDataModelTS(map);
                    
                switch(getScreenType(screenname)) {
                    case 1 : 
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeTSFile(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 2 :
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2TSFile(names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 3 : 
                            ArrayList<String> s3names = getTwoTableName(screenname);
                            ScreenmappingconditionMst  mappingObject = getScreenMappingCondition(screenname);
                            response.addProperty("data", s1.makeS3TSFile(s3names, mappingObject));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                  
            }
                
                    return response.toString();
            }
        
        @SuppressWarnings("unchecked")
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/get_api_file", produces = "application/json")
	@ApiOperation(value = "Get api file", httpMethod = "GET")
	@ApiResponse(code = 200, message = "Returns a 200 response code if successful")
	public String getAPIFile(@RequestParam String screenname) throws SQLException {
		JsonObject response = new JsonObject();
          
                      SimpleDataModelApi s1=new SimpleDataModelApi();
                    
                switch(getScreenType(screenname)) {
                    case 1 : 
                            String tableName = getOneTableName(screenname);
                            response.addProperty("data", s1.makeAPIFile(tableName));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 2 :
                            ArrayList<String> names = getTwoTableName(screenname);
                            response.addProperty("data", s1.makeS2APIFile(names));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                            
                    case 3 : 
                            ArrayList<String> S3TableNames = getTwoTableName(screenname);
                            ScreenmappingconditionMst  mappingObject = getScreenMappingCondition(screenname);
                            response.addProperty("data", s1.makeS3APIFile(S3TableNames, mappingObject));
                            response.addProperty("code", 200);
                            response.addProperty("status", "Success");
                            break;
                    
            }
                
                    return response.toString();
            }
        
        private int getScreenType(String screenName) {
             ScreenMst screen = getScreenObject(screenName);
             int x = screen.getScreentype();
             return x;
        }
        
        private int getScreenId(String screenName) {
            ScreenMst screen = getScreenObject(screenName);
             int x = screen.getScreenid();
             return x;
        }
        
        private String getOneTableName(String screenName) {          
            ScreenMst screen = getScreenObject(screenName);
            List<ScreengroupMst> screenGroupList = screen.getScreengroup();
            String tableName = "";
            for(ScreengroupMst screenGroup : screenGroupList) {
                tableName = screenGroup.getBasetable();
                break;
            }
            
            MapsUtil.constantsMap.put("table_name", tableName);
            MapsUtil.constantsMap.put("cap_table_name", SimpleDataModel.nameCase(tableName));
   
            return tableName;
        }
        
        private ArrayList<String> getTwoTableName(String screenName) {
            ScreenMst screen = getScreenObject(screenName);
            List<ScreengroupMst> screenGroupList = screen.getScreengroup();
           ArrayList<String> tableNames = new ArrayList<>();
            for(int i = 1; i <= screenGroupList.size(); i++) { 
                ScreengroupMst screenGroup = screenGroupList.get(i-1);
                String str1 = screenGroup.getBasetable();
                System.out.println("tableName" + i + " = " + str1);
                tableNames.add(str1);
                MapsUtil.constantsMap.put("table_name" + i, str1);
                MapsUtil.constantsMap.put("cap_table_name" + i, SimpleDataModel.nameCase(str1));
 
            }
            
            return tableNames;
        }
        
        private ScreenMst getScreenObject(String screenName) {
            return ScreenController.map1.get(screenName);
        } 
        
        private ScreenmappingconditionMst getScreenMappingCondition(String screenName) {
                int screenid = getScreenId(screenName);
                ScreenmappingconditionMst obj = getScreenMappingConditionObject(screenid);
                return obj;
        }
        
        public ScreenmappingconditionMst getScreenMappingConditionObject(int screenid)  {
                    JsonObject response = new JsonObject();
                    List<String> conditionList = new ArrayList<>();
                    String query = "";
                    ObjectMapper mapper = new ObjectMapper();
                    ScreenmappingconditionMst obj = new ScreenmappingconditionMst();
                    
                    try {
                                    query ="select screenid, screenmappingid, sourcegroupid, destgroupid, "
                                            + "masterfieldid, detailfieldid, masterquerycolumn, detailquerycolumn, mappingtable "
                                            + "query from screenmappingcondition where screenid = " + screenid + ";";
                                    
                                    conditionList = em.createNativeQuery(query).getResultList();
                                    System.out.println("json string = " + JsonParser.parseString(mapper.writeValueAsString(conditionList.get(0))));
                                    
                                    response.add("data", JsonParser.parseString(mapper.writeValueAsString(conditionList.get(0))));
                                    JsonNode jsonNode = mapper.readTree(response.get("data").toString());
                                    
                                    obj.setScreenid(jsonNode.get(0).asInt());
                                    obj.setScreenmappingid(jsonNode.get(1).asInt());
                                    obj.setSourcegroupid(jsonNode.get(2).asInt());
                                    obj.setDestgroupid(jsonNode.get(3).asInt());
                                    obj.setMasterfieldid(jsonNode.get(4).asInt());
                                    obj.setDetailfieldid(jsonNode.get(5).asInt());
                                    obj.setMasterquerycolumn(jsonNode.get(6).asText());
                                    obj.setDetailquerycolumn(jsonNode.get(7).asText());
                                    obj.setMappingtable(jsonNode.get(8).asText());
                                    obj.setQuery(jsonNode.get(9).asText());

                    
                    } catch (Exception ex) {
                            ex.printStackTrace();
                            response.add("data", new JsonArray());
                    }

                    return obj;
        }
}
