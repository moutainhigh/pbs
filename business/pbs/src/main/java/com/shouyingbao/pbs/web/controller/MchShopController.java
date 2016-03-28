package com.shouyingbao.pbs.web.controller;

import com.shouyingbao.pbs.Exception.UserNotFoundException;
import com.shouyingbao.pbs.constants.ConstantEnum;
import com.shouyingbao.pbs.core.bean.ResponseData;
import com.shouyingbao.pbs.core.common.util.DateUtil;
import com.shouyingbao.pbs.entity.MchCompany;
import com.shouyingbao.pbs.entity.MchShop;
import com.shouyingbao.pbs.service.MchCompanyService;
import com.shouyingbao.pbs.service.MchShopService;
import com.shouyingbao.pbs.service.MchSubCompanyService;
import com.shouyingbao.pbs.vo.MchShopVO;
import com.shouyingbao.pbs.vo.MchSubCompanyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * kejun
 * 2016/3/17 11:05
 **/
@Controller
@RequestMapping("/mchShop")
public class MchShopController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(MchShopController.class);

    @Autowired
    MchShopService mchShopService;

    @Autowired
    MchCompanyService mchCompanyService;

    @Autowired
    MchSubCompanyService mchSubCompanyService;

    @RequestMapping(value = "/search")
    public String search() {
        return "/mchShop/mchShop";
    }

    @RequestMapping("/list")
    public String list(ModelMap model,@RequestBody Map<String,Object> map){
        LOGGER.info("list:map={}", map);
        try {
            Integer currpage = Integer.valueOf(map.get("currpage").toString());
            List<MchShopVO> shopVOList = mchShopService.selectListByPage(map, currpage, ConstantEnum.LIST_PAGE_SIZE.getCodeInt());
            Integer totalCount = mchShopService.selectListCount(map);
            model.addAttribute("rowCount", getRowCount(totalCount));
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("currpage", currpage);
            model.addAttribute("list", shopVOList);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return "mchShop/list";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap modelMap, Integer id) {
        LOGGER.info("edit:id={}",id);
        MchShopVO mchShopVO = new MchShopVO();
        if (id != null && id > 0) {
            MchShop mchShop = mchShopService.selectById(id);
            BeanUtils.copyProperties(mchShop, mchShopVO);
        }
        List<MchCompany> mchCompanyList = mchCompanyService.selectListByPage(new HashMap<String, Object>(), null, null);
        List<MchSubCompanyVO> mchSubCompanyVOList = mchSubCompanyService.selectListByPage(new HashMap<String, Object>(), null, null);
        mchShopVO.setCompanyList(mchCompanyList);
        mchShopVO.setSubCompanyVOList(mchSubCompanyVOList);
        modelMap.addAttribute("entity", mchShopVO);
        return "mchShop/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResponseData save(MchShop mchShop){
        LOGGER.info("save:mchShop={}", mchShop);
        try {

            if (mchShop.getId() == null) {
                mchShop.setCreateAt(DateUtil.getCurrDateTime());
                mchShop.setCreateBy(getUser().getId());
                mchShopService.insert(mchShop);
            } else {
                mchShop.setUpdateAt(DateUtil.getCurrDateTime());
                mchShop.setUpdateBy(getUser().getId());
                mchShopService.update(mchShop);
            }
            return ResponseData.success();
        }catch (UserNotFoundException e){
            return ResponseData.failure(e.getCode(),e.getMessage());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseData.failure(ConstantEnum.EXCEPTION_INSERT_FAIL.getCodeStr(),ConstantEnum.EXCEPTION_INSERT_FAIL.getValueStr());
        }

    }

    @RequestMapping("/cance")
    @ResponseBody
    public ResponseData cance(Integer id) {
        LOGGER.info("cance:id={}", id);
        try {
            MchShop mchShop = mchShopService.selectById(id);
            mchShop.setUpdateAt(DateUtil.getCurrDateTime());
            mchShop.setUpdateBy(getUser().getId());
            mchShop.setIsDelete(ConstantEnum.IS_DELETE_1.getCodeByte());
            mchShopService.update(mchShop);
            return ResponseData.success();
        } catch (UserNotFoundException e) {
            return ResponseData.failure(e.getCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseData.failure(ConstantEnum.EXCEPTION_CANCE_FAIL.getCodeStr(), ConstantEnum.EXCEPTION_CANCE_FAIL.getValueStr());
        }
    }

    @RequestMapping("/getByCompanyId")
    @ResponseBody
    public ResponseData getByCompanyId(Integer parentId) {
        LOGGER.info("getByCompanyId:parentId={}", parentId);
        try {
            List<MchShop> mchShopList = mchShopService.selectByCompanyId(parentId);
            return ResponseData.success(mchShopList);
        } catch (UserNotFoundException e) {
            return ResponseData.failure(e.getCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseData.failure(ConstantEnum.EXCEPTION_OPERATION_FAIL.getCodeStr(), ConstantEnum.EXCEPTION_OPERATION_FAIL.getValueStr());
        }

    }

    @RequestMapping("/getBySubCompanyId")
    @ResponseBody
    public ResponseData getBySubCompanyId(Integer parentId) {
        LOGGER.info("getBySubCompanyId:parentId={}", parentId);
        try {
            List<MchShop> mchShopList = mchShopService.selectBySubCompanyId(parentId);
            return ResponseData.success(mchShopList);
        } catch (UserNotFoundException e) {
            return ResponseData.failure(e.getCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return ResponseData.failure(ConstantEnum.EXCEPTION_OPERATION_FAIL.getCodeStr(), ConstantEnum.EXCEPTION_OPERATION_FAIL.getValueStr());
        }

    }

}
